package com.computeca.bncc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.computeca.bncc.model.Habilidade;
import com.computeca.bncc.repository.HabilidadeRepository;

@RestController
@RequestMapping("/api/habilidades")
public class HabilidadeController {

    @Autowired
    private HabilidadeRepository habilidadeRepository;

    @GetMapping
    public List<Habilidade> getAllHabilidades() {
        return habilidadeRepository.findAll();
    }
}