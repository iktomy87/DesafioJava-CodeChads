package com.info.moodtrack.service.usuario;

import com.info.moodtrack.dto.usuario.UsuarioCreateDto;
import com.info.moodtrack.dto.usuario.UsuarioDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {
    List<UsuarioDto> obtenerTodos();
    Optional<UsuarioDto> obtenerPorId(UUID id);
    UsuarioDto crearUsuario(UsuarioCreateDto usuarioCreateDto);

    UsuarioDto updateUsuario(UUID id, UsuarioCreateDto usuarioCreateDto);

    boolean eliminarUsuario(UUID id);
}
