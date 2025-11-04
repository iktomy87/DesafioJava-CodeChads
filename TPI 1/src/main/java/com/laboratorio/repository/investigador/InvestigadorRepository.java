package com.laboratorio.repository.investigador;

import com.laboratorio.dominio.Investigador;
import java.util.List;
import java.util.Optional;

public interface InvestigadorRepository {
    void guardar(Investigador investigador);
    Optional<Investigador> buscarPorNombre(String nombre);
    List<Investigador> obtenerTodos();
}
