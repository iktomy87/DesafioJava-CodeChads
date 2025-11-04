package com.laboratorio.dominio;

import java.util.ArrayList;
import java.util.List;

public class Investigador {
    private String nombre;
    private int edad;
    private int cantidadExperimentos;

    public Investigador(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.cantidadExperimentos = 0;
    }

    public void incrementarExperimentos() {
        this.cantidadExperimentos++;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getCantidadExperimentos() {
        return cantidadExperimentos;
    }

    @Override
    public String toString() {
        return "Investigador: " + nombre + " (Edad: " + edad + ")";
    }
}
