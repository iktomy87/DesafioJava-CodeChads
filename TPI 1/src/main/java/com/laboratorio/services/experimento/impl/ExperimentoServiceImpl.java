package com.laboratorio.services.experimento.impl;

import com.laboratorio.dominio.*;
import com.laboratorio.repository.experimento.ExperimentoRepository;
import com.laboratorio.repository.investigador.InvestigadorRepository;
import com.laboratorio.services.experimento.IExperimentoService;
import java.util.List;
import java.util.Optional;

public class ExperimentoServiceImpl implements IExperimentoService {

    private final ExperimentoRepository experimentoRepository;
    private final InvestigadorRepository investigadorRepository; // Necesita este para buscar

    public ExperimentoServiceImpl(ExperimentoRepository experimentoRepository, InvestigadorRepository investigadorRepository) {
        this.experimentoRepository = experimentoRepository;
        this.investigadorRepository = investigadorRepository;
    }

    @Override
    public boolean registrarExperimentoQuimico(String nombre, int duracion, ResultadoExperimento resultado, 
                                               String tipoReactivo, String nombreInvestigador) {
        
        Optional<Investigador> invOpt = investigadorRepository.buscarPorNombre(nombreInvestigador);
        if (invOpt.isEmpty()) {
            return false; // Falla: Investigador no encontrado
        }

        ExperimentoQuimico exp = new ExperimentoQuimico();
        exp.setNombre(nombre);
        exp.setDuracion(duracion);
        exp.setResultado(resultado);
        exp.setTipoReactivo(tipoReactivo);
        exp.setInvestigador(invOpt.get());
        
        // Lógica de negocio (OCP-compliant)
        exp.actualizarContadoresParticipantes();
        
        experimentoRepository.guardar(exp);
        return true;
    }

    @Override
    public boolean registrarExperimentoFisico(String nombre, int duracion, ResultadoExperimento resultado, 
                                              String instrumento, List<Investigador> investigadores) {
        
        if (investigadores.isEmpty()) {
            return false; // Falla: Regla de negocio (mínimo 1)
        }

        ExperimentoFisico exp = new ExperimentoFisico();
        exp.setNombre(nombre);
        exp.setDuracion(duracion);
        exp.setResultado(resultado);
        exp.setInstrumento(instrumento);
        exp.setInvestigadores(investigadores);
        
        // Lógica de negocio (OCP-compliant)
        exp.actualizarContadoresParticipantes();
        
        experimentoRepository.guardar(exp);
        return true;
    }

    @Override
    public List<Experimento> obtenerExperimentos() {
        return experimentoRepository.obtenerTodos();
    }
}
