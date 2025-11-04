package com.laboratorio.dominio;

public class ExperimentoQuimico extends Experimento {
    private String tipoReactivo;
    private Investigador investigador;

    @Override
    public String getTipo() {
        return "Químico";
    }

    @Override
    public void actualizarContadoresParticipantes() {
        this.investigador.incrementarExperimentos();
    }

    public String getTipoReactivo() {
        return tipoReactivo;
    }

    public void setTipoReactivo(String tipoReactivo) {
        this.tipoReactivo = tipoReactivo;
    }

    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }
}
