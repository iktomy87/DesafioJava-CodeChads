package com.laboratorio.repository.investigador.impl;

import com.laboratorio.dominio.Investigador;
import com.laboratorio.repository.investigador.InvestigadorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InvestigadorRepositoryImpl implements InvestigadorRepository {

    private final List<Investigador> investigadores = new ArrayList<>();

    @Override
    public void guardar(Investigador investigador) {
        investigadores.add(investigador);
    }

    @Override
    public Optional<Investigador> buscarPorNombre(String nombre) {
        return investigadores.stream()
                .filter(inv -> inv.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    @Override
    public List<Investigador> obtenerTodos() {
        return new ArrayList<>(investigadores); // Devuelve copia
    }
}
