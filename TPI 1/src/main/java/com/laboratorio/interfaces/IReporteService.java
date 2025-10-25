package com.laboratorio.interfaces;

public interface IReporteService {
    void generarReporteGeneral();
    void exportarInvestigadoresCSV(String rutaArchivo);
}