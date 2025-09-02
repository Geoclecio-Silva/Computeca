package com.computeca.bncc.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.computeca.bncc.model.Atividade;
import com.computeca.bncc.repository.AtividadeRepository;
import com.computeca.bncc.service.ServicoImagem;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AtividadeRepository atividadeRepository;
    private final ServicoImagem servicoImagem;

    public AdminController(AtividadeRepository atividadeRepository, ServicoImagem servicoImagem) {
        this.atividadeRepository = atividadeRepository;
        this.servicoImagem = servicoImagem;
    }

    // Lista todas as atividades
    @GetMapping
    public String listarAtividades(Model model) {
        model.addAttribute("atividades", atividadeRepository.findAll());
        return "admin/lista-atividades";
    }

    // Formulário de cadastro
    @GetMapping("/cadastro")
    public String exibirFormularioCadastro(Model model) {
        Atividade atividade = new Atividade();
        model.addAttribute("atividade", atividade);
        return "admin/formulario-atividade";
    }

    // Salvar nova atividade
    @PostMapping("/cadastro")
    public String cadastrarAtividade(@ModelAttribute Atividade atividade) throws IOException {
        // Converte a string de habilidades para lista
        if (atividade.getHabilidadesComoString() != null && !atividade.getHabilidadesComoString().isBlank()) {
            List<String> habilidades = Arrays.stream(atividade.getHabilidadesComoString().split("\\s*,\\s*"))
                                             .collect(Collectors.toList());
            atividade.setHabilidadesBncc(habilidades);
        }

        // Define a etapa a partir do campo transient
        atividade.setEtapaEducacional(atividade.getEtapaComoString());

        // Salva a imagem se houver
        if (atividade.getImagem() != null && !atividade.getImagem().isEmpty()) {
            String urlImagem = servicoImagem.salvarImagem(atividade.getImagem());
            atividade.setUrlImagem(urlImagem);
        }

        atividadeRepository.save(atividade);
        return "redirect:/admin";
    }

    // Formulário de edição
    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        Atividade atividade = atividadeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de atividade inválido: " + id));

        // Popula campos transient usados pelo formulário
        atividade.setEtapaComoString(atividade.getEtapaEducacional());
        if (atividade.getHabilidadesBncc() != null && !atividade.getHabilidadesBncc().isEmpty()) {
            atividade.setHabilidadesComoString(String.join(", ", atividade.getHabilidadesBncc()));
        }

        model.addAttribute("atividade", atividade);
        return "admin/formulario-atividade";
    }

    // Salvar edição
    @PostMapping("/editar/{id}")
    public String editarAtividade(@PathVariable Long id, @ModelAttribute Atividade atividade) throws IOException {
        Atividade existente = atividadeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de atividade inválido: " + id));

        // Atualiza campos básicos
        existente.setNome(atividade.getNome());
        existente.setDescricao(atividade.getDescricao());
        existente.setCategoria(atividade.getCategoria());
        existente.setLink(atividade.getLink());

        // Atualiza etapa e habilidades
        existente.setEtapaEducacional(atividade.getEtapaComoString());
        if (atividade.getHabilidadesComoString() != null && !atividade.getHabilidadesComoString().isBlank()) {
            List<String> habilidades = Arrays.stream(atividade.getHabilidadesComoString().split("\\s*,\\s*"))
                                             .collect(Collectors.toList());
            existente.setHabilidadesBncc(habilidades);
        }

        // Atualiza imagem se enviada
        if (atividade.getImagem() != null && !atividade.getImagem().isEmpty()) {
            String urlImagem = servicoImagem.salvarImagem(atividade.getImagem());
            existente.setUrlImagem(urlImagem);
        }

        atividadeRepository.save(existente);
        return "redirect:/admin";
    }

    // Confirmar exclusão
    @GetMapping("/apagar/{id}")
    public String confirmarExclusao(@PathVariable Long id, Model model) {
        Atividade atividade = atividadeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de atividade inválido: " + id));
        model.addAttribute("atividade", atividade);
        return "admin/confirmar-exclusao";
    }

    // Deletar atividade
    @PostMapping("/apagar/{id}")
    public String apagarAtividade(@PathVariable Long id) {
        atividadeRepository.deleteById(id);
        return "redirect:/admin";
    }
}
