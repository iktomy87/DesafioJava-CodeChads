package com.laboratorio.repository.experimento;

import com.laboratorio.dominio.Experimento;
import java.util.List;

public interface ExperimentoRepository {
    void guardar(Experimento experimento);
    List<Experimento> obtenerTodos();
}
