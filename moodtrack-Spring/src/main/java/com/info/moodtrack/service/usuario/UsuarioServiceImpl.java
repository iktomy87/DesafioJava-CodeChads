package com.info.moodtrack.service.usuario;

import com.info.moodtrack.dto.usuario.UsuarioCreateDto;
import com.info.moodtrack.dto.usuario.UsuarioDto;
import com.info.moodtrack.mapper.perfil.PerfilMapper;
import com.info.moodtrack.mapper.usuario.UsuarioMapper;
import com.info.moodtrack.model.PerfilUsuario;
import com.info.moodtrack.model.Usuario;
import com.info.moodtrack.repository.usuario.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioDto> obtenerTodos() {
        List<Usuario> usuarioList = usuarioRepository.findAll();

        return UsuarioMapper.toDtoList( usuarioList );
    }

    @Override
    public Optional<UsuarioDto> obtenerPorId(UUID id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuario usuarioEntity = usuario.get();
            return Optional.of( UsuarioMapper.toDto(usuarioEntity) );
        }
        return Optional.empty();
    }

    @Override
    public UsuarioDto crearUsuario(UsuarioCreateDto usuarioCreateDto) {
        Usuario usuario = UsuarioMapper.toEntity( usuarioCreateDto );
        usuario = usuarioRepository.save( usuario );
        return UsuarioMapper.toDto( usuario );
    }

    @Override
    public UsuarioDto updateUsuario(UUID id, UsuarioCreateDto usuarioCreateDto) {
        //1. Buscar el usuario por id
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            Optional<Usuario> usuarioExist = usuarioRepository.findByEmail( usuarioCreateDto.getEmail() );

            if (usuarioExist.isPresent() && !usuarioExist.get().getId().equals( id )) {
                throw new IllegalArgumentException("Mail no disponible");
            }

            //2. Setear campo a campo para actualizarlo
            Usuario usuarioEntity = usuario.get();
            usuarioEntity.setNombre( usuarioCreateDto.getNombre() );
            usuarioEntity.setEmail( usuarioCreateDto.getEmail() );

            PerfilUsuario perfilUsuario = usuarioEntity.getPerfil();
            //3. Chequear si no tiene perfil crearlo, sino actualizar sus campos
            if(perfilUsuario == null) {
                perfilUsuario = PerfilMapper.toEntity( usuarioCreateDto.getPerfilUsuarioDto() );
                usuarioEntity.setPerfil( perfilUsuario );
            }else{
                perfilUsuario.setBio( usuarioCreateDto.getPerfilUsuarioDto().getBio() );
                perfilUsuario.setColorFavorito( usuarioCreateDto.getPerfilUsuarioDto().getColorFavorito() );
                perfilUsuario.setFraseDelDia( usuarioCreateDto.getPerfilUsuarioDto().getFraseDelDia() );
            }

            //4. Guardarlo (actualizarlo)
            Usuario usuarioActualizado = usuarioRepository.save( usuarioEntity );
            log.info("Usuario actualizado con id {}", usuarioActualizado.getId());

            //5 devolver el UsuarioDTO.
            return UsuarioMapper.toDto( usuarioActualizado );

        }
        return null;
    }

    @Override
    public boolean eliminarUsuario(UUID id) {

        if ( usuarioRepository.existsById(id) ){
            usuarioRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
