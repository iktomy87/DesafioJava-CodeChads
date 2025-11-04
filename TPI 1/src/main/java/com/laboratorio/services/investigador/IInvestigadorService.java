package com.laboratorio.services.investigador;

import com.laboratorio.dominio.Investigador;
import java.util.List;

public interface IInvestigadorService {
    void registrarInvestigador(String nombre, int edad);
    List<Investigador> obtenerInvestigadores();
}
