package com.info.moodtrack.service.habito.impl;

import com.info.moodtrack.dto.habito.HabitoCreateDto;
import com.info.moodtrack.dto.habito.HabitoDto;
import com.info.moodtrack.mapper.habito.HabitoMapper;
import com.info.moodtrack.model.Habito;
import com.info.moodtrack.repository.habito.HabitoRepository;
import com.info.moodtrack.service.habito.HabitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitoServiceImpl implements HabitoService {

    private final HabitoRepository habitoRepository;

    @Autowired
    public HabitoServiceImpl(HabitoRepository habitoRepository) {
        this.habitoRepository = habitoRepository;
    }

    @Override
    public HabitoDto createHabito(HabitoCreateDto habitoCreateDto) {
        Habito habito = HabitoMapper.toEntity(habitoCreateDto);
        habito = habitoRepository.save(habito);
        return HabitoMapper.toDto(habito);
    }

    @Override
    public List<HabitoDto> getAllHabitos() {
        List<Habito> habitos = habitoRepository.findAll();
        return HabitoMapper.toDtoList(habitos);
    }
}
