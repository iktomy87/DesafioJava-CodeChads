package com.laboratorio.services.reporte.impl;

import com.laboratorio.dominio.Experimento;
import com.laboratorio.dominio.Investigador;
import com.laboratorio.dominio.ResultadoExperimento;
import com.laboratorio.repository.experimento.ExperimentoRepository;
import com.laboratorio.repository.investigador.InvestigadorRepository;
import com.laboratorio.services.reporte.IReporteService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReporteServicioImpl implements IReporteService {

    private final InvestigadorRepository investigadorRepository;
    private final ExperimentoRepository experimentoRepository;

    public ReporteServicioImpl(InvestigadorRepository investigadorRepository, ExperimentoRepository experimentoRepository) {
        this.investigadorRepository = investigadorRepository;
        this.experimentoRepository = experimentoRepository;
    }

    @Override
    public void generarReporteGeneral() {
        List<Experimento> experimentos = experimentoRepository.obtenerTodos();
        List<Investigador> investigadores = investigadorRepository.obtenerTodos();

        if (experimentos.isEmpty()) {
            System.out.println("No hay datos suficientes para generar un reporte.");
            return;
        }

        double promedioDuracion = getPromedioDuracion(experimentos);
        double porcentajeExito = getPorcentajeExito(experimentos);
        Optional<Investigador> invOpt = getInvestigadorMasActivo(investigadores);

        System.out.printf("Promedio de duración de experimentos: %.2f minutos\n", promedioDuracion);
        System.out.printf("Porcentaje de éxito global: %.2f%%\n", porcentajeExito);
        invOpt.ifPresent(inv -> System.out.println("Investigador más activo: " + inv.getNombre()));
    }

    @Override
    public void exportarInvestigadoresCSV(String rutaArchivo) {
        List<Investigador> investigadores = investigadorRepository.obtenerTodos();
        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo))) {
            pw.println("nombre,edad,cantidad de experimentos");
            for (Investigador inv : investigadores) {
                pw.printf("%s,%d,%d\n",
                        inv.getNombre(),
                        inv.getEdad(),
                        inv.getCantidadExperimentos());
            }
            System.out.println("Investigadores exportados correctamente a " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al exportar a CSV: " + e.getMessage());
        }
    }

    @Override
    public void mostrarEstadisticasResultados() {
        List<Experimento> experimentos = experimentoRepository.obtenerTodos();
        if (experimentos.isEmpty()) {
            System.out.println("No hay experimentos para mostrar estadísticas.");
            return;
        }
        Map<ResultadoExperimento, Long> estadisticas = getEstadisticasResultados(experimentos);
        System.out.println("Estadísticas de resultados:");
        estadisticas.forEach((resultado, cantidad) ->
                System.out.println("- " + resultado + ": " + cantidad + " experimentos")
        );
    }

    @Override
    public void mostrarExperimentoMasLargo() {
        List<Experimento> experimentos = experimentoRepository.obtenerTodos();
        if (experimentos.isEmpty()) {
            System.out.println("No hay experimentos registrados.");
            return;
        }
        Optional<Experimento> expOpt = getExperimentoMayorDuracion(experimentos);
        expOpt.ifPresent(exp ->
                System.out.println("Experimento más largo: " + exp.getNombre() + " (" + exp.getDuracion() + " minutos)")
        );
    }

    private double getPromedioDuracion(List<Experimento> experimentos) {
        return experimentos.stream()
                .mapToInt(Experimento::getDuracion)
                .average()
                .orElse(0.0);
    }

    private double getPorcentajeExito(List<Experimento> experimentos) {
        long exitosos = experimentos.stream()
                .filter(e -> e.getResultado() == ResultadoExperimento.EXITO)
                .count();
        return (double) exitosos / experimentos.size() * 100;
    }

    private Optional<Investigador> getInvestigadorMasActivo(List<Investigador> investigadores) {
        return investigadores.stream()
                .max(Comparator.comparingInt(Investigador::getCantidadExperimentos));
    }
    
    private Map<ResultadoExperimento, Long> getEstadisticasResultados(List<Experimento> experimentos) {
        return experimentos.stream()
                .collect(Collectors.groupingBy(
                        Experimento::getResultado,
                        Collectors.counting()
                ));
    }

    private Optional<Experimento> getExperimentoMayorDuracion(List<Experimento> experimentos) {
        return experimentos.stream()
                .max(Comparator.comparingInt(Experimento::getDuracion));
    }
}
