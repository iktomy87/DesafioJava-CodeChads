package com.info.moodtrack.dto.usuario;

import com.info.moodtrack.dto.perfil.PerfilUsuarioDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreateDto {

    @NotBlank(message = "El nombre del usuario no puede ser vacio")
    private String nombre;

    @NotBlank(message = "El email del usuario no puede ser vacio")
    private String email;

    @Valid
    private PerfilUsuarioDto perfilUsuarioDto;
}
