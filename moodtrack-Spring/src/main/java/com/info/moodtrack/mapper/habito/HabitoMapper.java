package com.info.moodtrack.mapper.habito;

import com.info.moodtrack.dto.habito.HabitoCreateDto;
import com.info.moodtrack.dto.habito.HabitoDto;
import com.info.moodtrack.model.Habito;

import java.util.List;

public final class HabitoMapper {

    private HabitoMapper() {}

    public static Habito toEntity(HabitoCreateDto habitoCreateDto) {
        if (habitoCreateDto == null) {
            return null;
        }
        Habito habito = new Habito();
        habito.setDescripcion(habitoCreateDto.getDescripcion());
        habito.setNivelDeImportanciaEnum(habitoCreateDto.getNivelDeImportanciaEnum());
        return habito;
    }

    public static HabitoDto toDto(Habito habito) {
        if (habito == null) {
            return null;
        }
        return new HabitoDto(habito.getId(), habito.getDescripcion());
    }

    public static List<HabitoDto> toDtoList(List<Habito> habitos) {
        return habitos.stream()
                .map(HabitoMapper::toDto)
                .toList();
    }
}
