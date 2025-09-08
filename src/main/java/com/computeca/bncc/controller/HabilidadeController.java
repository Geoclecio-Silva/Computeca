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

        // Preenche a classe CSS para cada habilidade
        habilidades.forEach(habilidade -> {
            String etapa = habilidade.getEtapaEducacional();
            String classe;

            switch (etapa) {
                case "Educação Infantil" -> classe = "etapa-educacao-infantil";
                case "Ensino Fundamental I" -> classe = "etapa-ensino-fundamental-i";
                case "Ensino Fundamental II" -> classe = "etapa-ensino-fundamental-ii";
                case "Ensino Médio" -> classe = "etapa-ensino-medio"; // Remove o acento
                default -> classe = "";
            }

            habilidade.setEtapaClasse(classe);
            System.out.println("Habilidade " + habilidade.getCodigoHabilidade() + " -> Etapa Classe: " + habilidade.getEtapaClasse());
        });

        return habilidades;
    }
}
