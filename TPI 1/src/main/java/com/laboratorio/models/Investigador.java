package com.laboratorio.models;

import java.util.ArrayList;
import java.util.List;


public class Investigador {
    private String nombre;
    private int edad;
    private List<Experimento> experimentosRealizados;

    public Investigador(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.experimentosRealizados = new ArrayList<>();
    }

    public void agregarExperimento(Experimento experimento) {
        this.experimentosRealizados.add(experimento);
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getCantidadExperimentos() {
        return experimentosRealizados.size();
    }

    public List<Experimento> getExperimentosRealizados() {
        return experimentosRealizados;
    }

    @Override
    public String toString() {
        return "Investigador: " + nombre + " (Edad: " + edad + ")";
    }
}