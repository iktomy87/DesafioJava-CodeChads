package com.info.moodtrack.dto.perfil;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuarioDto {
    private Long id;
    @NotBlank(message = "La biografia no puede ser vacia")
    private String bio;
    @NotBlank(message = "El color favorito no puede ser vacio")
    private String colorFavorito;
    @NotBlank(message = "La Frase del dia no puede ser vacia")
    private String fraseDelDia;
}
