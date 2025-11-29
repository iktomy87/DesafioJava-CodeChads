package com.info.moodtrack.controller;

import com.info.moodtrack.dto.usuario.UsuarioCreateDto;
import com.info.moodtrack.dto.usuario.UsuarioDto;
import com.info.moodtrack.service.usuario.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@Slf4j
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioDto> getUsuarios() {

        List<UsuarioDto> usuarios = usuarioService.obtenerTodos();
        return usuarios;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getUsuarioById(
            @PathVariable(name = "id") UUID id
            ) {
        Optional<UsuarioDto> usuario = usuarioService.obtenerPorId(id);

        if( usuario.isPresent() ){
            return ResponseEntity.ok( usuario.get() ) ;
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<UsuarioDto> createUsuario(
            @Valid @RequestBody UsuarioCreateDto usuarioCreateDto
            ){
        UsuarioDto usuarioCreado = usuarioService.crearUsuario(usuarioCreateDto);
        return ResponseEntity.ok( usuarioCreado );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUsuario( @PathVariable(name = "id") UUID id, @Valid @RequestBody UsuarioCreateDto usuarioCreateDto){
        log.info("Solicitud para actualizar usuario con id {}", id);
        UsuarioDto usuarioDto = usuarioService.updateUsuario( id, usuarioCreateDto );
        if( usuarioDto == null ){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok( usuarioDto );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario( @PathVariable(name = "id") UUID id ){
        boolean wasDeleted = usuarioService.eliminarUsuario( id );
        if( !wasDeleted ){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


}
