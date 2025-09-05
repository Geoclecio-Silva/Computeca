package com.computeca.bncc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.computeca.bncc.model.Habilidade;
import com.computeca.bncc.repository.AtividadeRepository;
import com.computeca.bncc.repository.HabilidadeRepository;
import com.computeca.bncc.service.ServicoImagem;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AtividadeRepository atividadeRepository;
    private final HabilidadeRepository habilidadeRepository;
    private final ServicoImagem servicoImagem;

    public AdminController(AtividadeRepository atividadeRepository,
                           HabilidadeRepository habilidadeRepository,
                           ServicoImagem servicoImagem) {
        this.atividadeRepository = atividadeRepository;
        this.habilidadeRepository = habilidadeRepository;
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
        
        // CORREÇÃO: Agrupar habilidades por etapa
        List<Habilidade> todasHabilidades = habilidadeRepository.findAll();
        Map<String, List<Habilidade>> habilidadesPorEtapa = todasHabilidades.stream()
            .collect(Collectors.groupingBy(Habilidade::getEtapaEducacional));
        
        model.addAttribute("habilidadesPorEtapa", habilidadesPorEtapa);
        return "admin/formulario-atividade";
    }

    // Salvar nova atividade (ESTÁ CORRETO)
    @PostMapping("/cadastro")
    public String cadastrarAtividade(@ModelAttribute Atividade atividade,
                                     @RequestParam(value = "habilidadesIds", required = false) List<Long> habilidadesIds) throws IOException {
        
        // Converter IDs em objetos Habilidade
        if (habilidadesIds != null && !habilidadesIds.isEmpty()) {
            List<Habilidade> habilidades = habilidadeRepository.findAllById(habilidadesIds);
            atividade.setHabilidadesBncc(habilidades);
        } else {
            atividade.setHabilidadesBncc(new ArrayList<>());
        }

        atividade.setEtapaEducacional(atividade.getEtapaComoString());

        MultipartFile imagem = atividade.getImagem();
        if (imagem != null && !imagem.isEmpty()) {
            String urlImagem = servicoImagem.salvarImagem(imagem);
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

        // CORREÇÃO: Removido código desnecessário de conversão para string
        atividade.setEtapaComoString(atividade.getEtapaEducacional());

        // CORREÇÃO: Agrupar habilidades por etapa
        List<Habilidade> todasHabilidades = habilidadeRepository.findAll();
        Map<String, List<Habilidade>> habilidadesPorEtapa = todasHabilidades.stream()
            .collect(Collectors.groupingBy(Habilidade::getEtapaEducacional));
        
        model.addAttribute("atividade", atividade);
        model.addAttribute("habilidadesPorEtapa", habilidadesPorEtapa);

        return "admin/formulario-atividade";
    }

    // Salvar edição (ESTÁ CORRETO)
    @PostMapping("/editar/{id}")
    public String editarAtividade(@PathVariable Long id,
                                  @ModelAttribute Atividade atividade,
                                  @RequestParam(value = "habilidadesIds", required = false) List<Long> habilidadesIds) throws IOException {
        Atividade existente = atividadeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de atividade inválido: " + id));

        existente.setNome(atividade.getNome());
        existente.setDescricao(atividade.getDescricao());
        existente.setCategoria(atividade.getCategoria());
        existente.setLink(atividade.getLink());
        existente.setEtapaEducacional(atividade.getEtapaComoString());

        // Converter IDs em objetos Habilidade
        if (habilidadesIds != null && !habilidadesIds.isEmpty()) {
            List<Habilidade> habilidades = habilidadeRepository.findAllById(habilidadesIds);
            existente.setHabilidadesBncc(habilidades);
        } else {
            existente.setHabilidadesBncc(new ArrayList<>());
        }

        MultipartFile imagem = atividade.getImagem();
        if (imagem != null && !imagem.isEmpty()) {
            String urlImagem = servicoImagem.salvarImagem(imagem);
            existente.setUrlImagem(urlImagem);
        }

        atividadeRepository.save(existente);
        return "redirect:/admin";
    }

    // Confirmar exclusão
    @GetMapping("/apagar/{id}")
    public String confirmarExclusao(@PathVariable Long id, Model model) {
        Atividade atividade = atividadeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de atividade inválido:" + id));
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