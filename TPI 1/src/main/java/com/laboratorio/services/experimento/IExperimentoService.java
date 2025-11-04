package com.laboratorio.services.experimento;

import com.laboratorio.dominio.Experimento;
import com.laboratorio.dominio.Investigador;
import com.laboratorio.dominio.ResultadoExperimento;
import java.util.List;

public interface IExperimentoService {
    boolean registrarExperimentoQuimico(String nombre, int duracion, ResultadoExperimento resultado, 
                                        String tipoReactivo, String nombreInvestigador);
    
    boolean registrarExperimentoFisico(String nombre, int duracion, ResultadoExperimento resultado, 
                                       String instrumento, List<Investigador> investigadores);

    List<Experimento> obtenerExperimentos();
}
