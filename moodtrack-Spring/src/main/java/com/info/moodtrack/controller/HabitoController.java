package com.info.moodtrack.controller;

import com.info.moodtrack.dto.habito.HabitoCreateDto;
import com.info.moodtrack.dto.habito.HabitoDto;
import com.info.moodtrack.service.habito.HabitoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/habitos")
public class HabitoController {

    private final HabitoService habitoService;

    @Autowired
    public HabitoController(HabitoService habitoService) {
        this.habitoService = habitoService;
    }

    @PostMapping
    public ResponseEntity<HabitoDto> createHabito(@Valid @RequestBody HabitoCreateDto habitoCreateDto) {
        HabitoDto createdHabito = habitoService.createHabito(habitoCreateDto);
        return new ResponseEntity<>(createdHabito, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HabitoDto>> getAllHabitos() {
        List<HabitoDto> habitos = habitoService.getAllHabitos();
        return ResponseEntity.ok(habitos);
    }
}
