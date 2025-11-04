package com.laboratorio.dominio;

import java.util.List;

public class ExperimentoFisico extends Experimento {
    private String instrumento;
    private List<Investigador> investigadores;

    @Override
    public String getTipo() {
        return "Físico";
    }

    @Override
    public void actualizarContadoresParticipantes() {
        this.investigadores.forEach(Investigador::incrementarExperimentos);
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public List<Investigador> getInvestigadores() {
        return investigadores;
    }

    public void setInvestigadores(List<Investigador> investigadores) {
        this.investigadores = investigadores;
    }
}
