package com.laboratorio.services.reporte;

public interface IReporteService {
    void generarReporteGeneral();
    void exportarInvestigadoresCSV(String rutaArchivo);
    void mostrarEstadisticasResultados();
    void mostrarExperimentoMasLargo();
}
