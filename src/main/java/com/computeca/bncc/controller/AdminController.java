package com.computeca.bncc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.computeca.bncc.model.Atividade;
import com.computeca.bncc.repository.AtividadeRepository;

@Controller
@RequestMapping("/admin/atividades")
public class AdminController {

    @Autowired
    private AtividadeRepository atividadeRepository;

    // Lista de atividades
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("atividades", atividadeRepository.findAll());
        return "admin/listar";
    }

    // Novo formulário
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("atividade", new Atividade());
        return "admin/formulario";
    }

    // Editar formulário
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Atividade atividade = atividadeRepository.findById(id).orElseThrow();
        atividade.prepararParaEdicao(); // 🔑 prepara transient
        model.addAttribute("atividade", atividade);
        return "admin/formulario";
    }

    // Salvar (novo/edição)
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("atividade") Atividade atividade,
                         @RequestParam(value = "imagem", required = false) MultipartFile imagem) {

        // Atualiza os campos persistentes com base nos transient
        atividade.atualizarAPartirDoFormulario();

        // Se for edição, manter URL antiga caso não troque imagem
        if (atividade.getId() != null) {
            Atividade existente = atividadeRepository.findById(atividade.getId()).orElseThrow();
            if (atividade.getUrlImagem() == null || atividade.getUrlImagem().isBlank()) {
                atividade.setUrlImagem(existente.getUrlImagem());
            }
        }

        atividadeRepository.save(atividade);
        return "redirect:/admin/atividades";
    }

    // Deletar
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        atividadeRepository.deleteById(id);
        return "redirect:/admin/atividades";
    }
}
