package com.laboratorio.services.menu.impl;

import com.laboratorio.dominio.Experimento;
import com.laboratorio.dominio.Investigador;
import com.laboratorio.dominio.ResultadoExperimento;
import com.laboratorio.repository.investigador.InvestigadorRepository;
import com.laboratorio.services.experimento.IExperimentoService;
import com.laboratorio.services.investigador.IInvestigadorService;
import com.laboratorio.services.menu.IMenuService;
import com.laboratorio.services.reporte.IReporteService;
import com.laboratorio.utils.InputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuServicioImpl implements IMenuService {

    // Depende de las interfaces de servicio
    private final IInvestigadorService investigadorServicio;
    private final IExperimentoService experimentoServicio;
    private final IReporteService reporteServicio;
    
    // Necesitamos el repositorio aquí para hacer búsquedas al crear experimentos
    private final InvestigadorRepository investigadorRepository;

    public MenuServicioImpl(IInvestigadorService investigadorServicio,
                            IExperimentoService experimentoServicio,
                            IReporteService reporteServicio,
                            InvestigadorRepository investigadorRepository) {
        this.investigadorServicio = investigadorServicio;
        this.experimentoServicio = experimentoServicio;
        this.reporteServicio = reporteServicio;
        this.investigadorRepository = investigadorRepository;
    }

    @Override
    public void iniciar() {
        // 1. Mostramos el logo UNA SOLA VEZ, antes de que inicie el bucle.
        mostrarLogo();
        
        int opcion;
        do {
            // 2. Mostramos las opciones en CADA iteración del bucle.
            mostrarOpcionesMenu();
            opcion = InputUtil.leerInt("Seleccione una opción: ");

            limpiarConsola();
            
            switch (opcion) {
                case 1: registrarInvestigador(); break;
                case 2: registrarExperimento(); break;
                case 3: listarExperimentos(); break;
                case 4: mostrarEstadisticasResultados(); break;
                case 5: mostrarExperimentoMasLargo(); break;
                case 6: generarReporteGeneral(); break;
                case 7: exportarInvestigadores(); break;
                case 0: System.out.println("Saliendo del sistema..."); break;
                default: System.out.println("Opción no válida. Intente de nuevo.");
            }
            // Solo imprimimos un separador si la opción no es salir
            if (opcion != 0) {
                 System.out.println("----------------------------------------");
            }
        } while (opcion != 0);
    }

    private void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    /**
     * Método que solo imprime el logo y el título principal.
     */
    private void mostrarLogo() {
    String logo = " __       _                     _             _           ___ _               _ \n"
        + "  / /  __ _| |__   ___  _ __ __ _| |_ ___  _ __(_) ___     / __\\ |__   __ _  __| |\n"
        + " / /  / _` | '_ \\ / _ \\| '__/ _` | __/ _ \\| '__| |/ _ \\   / /  | '_ \\ / _` |/ _` |\n"
        + "/ /__| (_| | |_) | (_) | | | (_| | || (_) | |  | | (_) | / /___| | | | (_| | (_| |\n"
        + "\\____/\\__,_|_.__/ \\___/|_|  \\__,_|\\__\\___/|_|  |_|\\___/  \\____/|_| |_|\\__,_|\\__,_|\n"
        + "                                                                                  \n";
        System.out.println(logo);
        System.out.println("=============================== Sistema de Gestion ===============================");
    }

    /**
     * Método renombrado que solo muestra las opciones del menú.
     */
    private void mostrarOpcionesMenu() {
        System.out.println("\n");
        System.out.println("1. Registrar Investigador");
        System.out.println("2. Registrar Experimento");
        System.out.println("3. Listar Experimentos");
        System.out.println("4. Mostrar Estadísticas de Resultados");
        System.out.println("5. Mostrar Experimento Más Largo");
        System.out.println("6. Generar Reporte General");
        System.out.println("7. Exportar Investigadores a CSV");
        System.out.println("0. Salir");
        System.out.println("\n");
    }


    private void registrarInvestigador() {
        System.out.println(">> 1. Registrar Investigador");
        String nombre = InputUtil.leerString("Nombre del investigador: ");
        int edad = InputUtil.leerInt("Edad: ");
        
        // Delega al servicio
        investigadorServicio.registrarInvestigador(nombre, edad);
        System.out.println("¡Investigador registrado con éxito!");
    }

    private void registrarExperimento() {
        int tipo = InputUtil.leerInt("Tipo (1 = Químico, 2 = Físico): ");
        String nombre = InputUtil.leerString("Nombre del experimento: ");
        int duracion = InputUtil.leerInt("Duración (en minutos): ");
        ResultadoExperimento resultado = InputUtil.leerResultado("Resultado (exito/fallo): ");

        if (tipo == 1) {
            registrarExperimentoQuimico(nombre, duracion, resultado);
        } else if (tipo == 2) {
            registrarExperimentoFisico(nombre, duracion, resultado);
        }
    }

    private void registrarExperimentoQuimico(String nombre, int duracion, ResultadoExperimento resultado) {
        String reactivo = InputUtil.leerString("Tipo de reactivo: ");
        String nombreInv = InputUtil.leerString("Nombre del (único) investigador: ");

        // Delega al servicio
        boolean exito = experimentoServicio.registrarExperimentoQuimico(nombre, duracion, resultado, reactivo, nombreInv);
        
        if (exito) {
            System.out.println("¡Experimento Químico registrado!");
        } else {
            System.out.println("Error: Investigador no encontrado.");
        }
    }

    private void registrarExperimentoFisico(String nombre, int duracion, ResultadoExperimento resultado) {
        String instrumento = InputUtil.leerString("Instrumento utilizado: ");
        List<Investigador> participantes = new ArrayList<>();
        
        System.out.println("Ingrese los nombres de los investigadores (mínimo 1). Escriba 'fin' para terminar:");
        while (true) {
            String nombreInv = InputUtil.leerString("Nombre investigador (o 'fin'): ");
            if (nombreInv.equalsIgnoreCase("fin")) break;
            
            // Usamos el repositorio (inyectado) para la búsqueda
            Optional<Investigador> invOpt = investigadorRepository.buscarPorNombre(nombreInv);
            if (invOpt.isPresent()) {
                participantes.add(invOpt.get());
                System.out.println(nombreInv + " añadido.");
            } else {
                System.out.println("Investigador '" + nombreInv + "' no encontrado.");
            }
        }

        // Delega al servicio
        boolean exito = experimentoServicio.registrarExperimentoFisico(nombre, duracion, resultado, instrumento, participantes);
        
        if (exito) {
            System.out.println("¡Experimento Físico registrado!");
        } else {
            System.out.println("Error: Un experimento físico debe tener al menos 1 investigador.");
        }
    }
    
    private void listarExperimentos() {
        System.out.println(">> 3. Listado de Experimentos");
        List<Experimento> lista = experimentoServicio.obtenerExperimentos();
        if (lista.isEmpty()) {
            System.out.println("No hay experimentos registrados.");
            return;
        }
        lista.forEach(System.out::println);
    }
    
    private void generarReporteGeneral() {
        System.out.println(">> 6. Reporte General");
        reporteServicio.generarReporteGeneral();
    }

    private void exportarInvestigadores() {
        System.out.println(">> 7. Exportar Investigadores a CSV");
        String ruta = InputUtil.leerString("Ingrese el nombre con el cual desea guardar el archivo: ");
        reporteServicio.exportarInvestigadoresCSV(ruta);
    }

    private void mostrarEstadisticasResultados() {
        System.out.println(">> 4. Mostrar Estadísticas de Resultados");
        reporteServicio.mostrarEstadisticasResultados();
    }

    private void mostrarExperimentoMasLargo() {
        System.out.println(">> 5. Mostrar Experimento Más Largo");
        reporteServicio.mostrarExperimentoMasLargo();
    }

}