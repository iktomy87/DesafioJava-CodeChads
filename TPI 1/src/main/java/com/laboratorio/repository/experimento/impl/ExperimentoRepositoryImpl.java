package com.laboratorio.repository.experimento.impl;

import com.laboratorio.dominio.Experimento;
import com.laboratorio.repository.experimento.ExperimentoRepository;
import java.util.ArrayList;
import java.util.List;

public class ExperimentoRepositoryImpl implements ExperimentoRepository {

    private final List<Experimento> experimentos = new ArrayList<>();

    @Override
    public void guardar(Experimento experimento) {
        experimentos.add(experimento);
    }

    @Override
    public List<Experimento> obtenerTodos() {
        return new ArrayList<>(experimentos);
    }
}
