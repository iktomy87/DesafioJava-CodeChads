package com.laboratorio.dominio;

public abstract class Experimento {
    protected String nombre;
    protected int duracion; 
    protected ResultadoExperimento resultado;

    public abstract String getTipo();
    
    public abstract void actualizarContadoresParticipantes();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public ResultadoExperimento getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoExperimento resultado) {
        this.resultado = resultado;
    }
}
