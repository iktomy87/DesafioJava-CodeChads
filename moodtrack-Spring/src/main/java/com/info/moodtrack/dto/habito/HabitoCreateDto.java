package com.info.moodtrack.dto.habito;

import com.info.moodtrack.model.NivelDeImportanciaEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HabitoCreateDto {
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    @NotNull(message = "El nivel de importancia no puede ser nulo")
    private NivelDeImportanciaEnum nivelDeImportanciaEnum;
}
