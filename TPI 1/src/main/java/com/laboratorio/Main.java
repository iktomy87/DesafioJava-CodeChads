package com.laboratorio;

import com.laboratorio.repository.experimento.ExperimentoRepository;
import com.laboratorio.repository.investigador.InvestigadorRepository;
import com.laboratorio.repository.experimento.impl.ExperimentoRepositoryImpl;
import com.laboratorio.repository.investigador.impl.InvestigadorRepositoryImpl;
import com.laboratorio.services.experimento.IExperimentoService;
import com.laboratorio.services.investigador.IInvestigadorService;
import com.laboratorio.services.menu.IMenuService;
import com.laboratorio.services.reporte.IReporteService;
import com.laboratorio.services.experimento.impl.ExperimentoServiceImpl;
import com.laboratorio.services.investigador.impl.InvestigadorServiceImpl;
import com.laboratorio.services.menu.impl.MenuServicioImpl;
import com.laboratorio.services.reporte.impl.ReporteServicioImpl;

public class Main {
    public static void main(String[] args) {
        
        InvestigadorRepository investigadorRepository = new InvestigadorRepositoryImpl();
        ExperimentoRepository experimentoRepository = new ExperimentoRepositoryImpl();

        IInvestigadorService investigadorServicio = new InvestigadorServiceImpl(investigadorRepository);
        IExperimentoService experimentoServicio = new ExperimentoServiceImpl(experimentoRepository, investigadorRepository);
        IReporteService reporteServicio = new ReporteServicioImpl(investigadorRepository, experimentoRepository);

        IMenuService menuServicio = new MenuServicioImpl(
                investigadorServicio, 
                experimentoServicio, 
                reporteServicio,
                investigadorRepository 
        );

        menuServicio.iniciar();
    }
}