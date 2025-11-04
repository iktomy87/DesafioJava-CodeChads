package com.laboratorio.services.investigador.impl;

import com.laboratorio.dominio.Investigador;
import com.laboratorio.repository.investigador.InvestigadorRepository;
import com.laboratorio.services.investigador.IInvestigadorService;
import java.util.List;

public class InvestigadorServiceImpl implements IInvestigadorService {

    private final InvestigadorRepository investigadorRepository;

    public InvestigadorServiceImpl(InvestigadorRepository investigadorRepository) {
        this.investigadorRepository = investigadorRepository;
    }

    @Override
    public void registrarInvestigador(String nombre, int edad) {
        Investigador nuevo = new Investigador(nombre, edad);
        investigadorRepository.guardar(nuevo);
    }

    @Override
    public List<Investigador> obtenerInvestigadores() {
        return investigadorRepository.obtenerTodos();
    }
}
