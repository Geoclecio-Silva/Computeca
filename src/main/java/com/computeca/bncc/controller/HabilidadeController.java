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
                case "Educação Infantil":
                    classe = "infantil";
                    break;
                case "Ensino Fundamental I":
                    classe = "fundamental-i";
                    break;
                case "Ensino Fundamental II":
                    classe = "fundamental-ii";
                    break;
                case "Ensino Médio":
                    classe = "medio";
                    break;
                default:
                    classe = "";
                    break;
            }

            habilidade.setEtapaClasse(classe);
            System.out.println("Habilidade " + habilidade.getCodigoHabilidade() + " -> Etapa Classe: " + habilidade.getEtapaClasse());
        });

        return habilidades;
    }
}
