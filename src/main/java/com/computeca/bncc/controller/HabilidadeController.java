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
        List<Habilidade> habilidades = habilidadeRepository.findAll();

        for (Habilidade habilidade : habilidades) {
            switch (habilidade.getEtapaEducacional()) {
                case "Educação Infantil" -> habilidade.setEtapaClasse("etapa-educacao-infantil");
                case "Ensino Fundamental I" -> habilidade.setEtapaClasse("etapa-ensino-fundamental-i");
                case "Ensino Fundamental II" -> habilidade.setEtapaClasse("etapa-ensino-fundamental-ii");
                case "Ensino Médio" -> habilidade.setEtapaClasse("etapa-ensino-medio");
                default -> habilidade.setEtapaClasse(""); // caso não bata com nenhum
            }

        }

        return habilidades;
    }
}
