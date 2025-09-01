package com.computeca.bncc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    @GetMapping
    public String listarAtividades(Model model) {
        model.addAttribute("atividades", atividadeRepository.findAll());
        return "admin/lista-atividades";
    }

    @GetMapping("/cadastro")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("atividade", new Atividade());
        return "admin/formulario-atividade";
    }

    @PostMapping("/cadastro")
    public String cadastrarAtividade(Atividade atividade) throws IOException {
        // Lógica de habilidades BNCC
        if (atividade.getHabilidadesBncc() != null && !atividade.getHabilidadesBncc().isEmpty()) {
            List<String> habilidades = Arrays.stream(atividade.getHabilidadesBncc().get(0).split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
            atividade.setHabilidadesBncc(habilidades);
        } else {
            atividade.setHabilidadesBncc(new ArrayList<>());
        }

        // Lógica de imagem
        if (atividade.getImagem() != null && !atividade.getImagem().isEmpty()) {
            String urlImagem = servicoImagem.salvarImagem(atividade.getImagem());
            atividade.setUrlImagem(urlImagem);
        }

        atividadeRepository.save(atividade);
        return "redirect:/admin";
    }

    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        Atividade atividade = atividadeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de atividade inválido:" + id));

        // Converte a lista de habilidades em uma única string para o formulário
        String habilidadesComoString = String.join(", ", atividade.getHabilidadesBncc());
        model.addAttribute("habilidadesComoString", habilidadesComoString);

        model.addAttribute("atividade", atividade);
        return "admin/formulario-atividade";
    }

    @PostMapping("/editar/{id}")
    public String editarAtividade(@PathVariable Long id, @ModelAttribute Atividade atividadeDoForm) throws IOException {
        Optional<Atividade> atividadeExistenteOpt = atividadeRepository.findById(id);

        if (atividadeExistenteOpt.isPresent()) {
            Atividade atividadeExistente = atividadeExistenteOpt.get();

            // Atualiza os campos que não precisam de lógica especial
            atividadeExistente.setNome(atividadeDoForm.getNome());
            atividadeExistente.setDescricao(atividadeDoForm.getDescricao());
            atividadeExistente.setCategoria(atividadeDoForm.getCategoria());
            atividadeExistente.setLink(atividadeDoForm.getLink());

            // Lógica de habilidades BNCC (CORRIGIDA)
            // Apenas atualiza se o valor do formulário não for nulo
            if (atividadeDoForm.getHabilidadesBncc() != null) {
                String habilidadesComoString = atividadeDoForm.getHabilidadesBncc().get(0);
                if (habilidadesComoString != null && !habilidadesComoString.trim().isEmpty()) {
                    List<String> habilidades = Arrays.stream(habilidadesComoString.split(","))
                            .map(String::trim)
                            .collect(Collectors.toList());
                    atividadeExistente.setHabilidadesBncc(habilidades);
                } else {
                     // Se o campo for deixado em branco, a lista será esvaziada. 
                     // Se você quer manter as antigas, remova esta linha.
                    atividadeExistente.setHabilidadesBncc(new ArrayList<>());
                }
            }

            // Lógica para etapa educacional (CORRIGIDA)
            if (atividadeDoForm.getEtapaEducacional() != null) {
                atividadeExistente.setEtapaEducacional(atividadeDoForm.getEtapaEducacional());
            }

            // Lógica de imagem
            if (atividadeDoForm.getImagem() != null && !atividadeDoForm.getImagem().isEmpty()) {
                String urlImagem = servicoImagem.salvarImagem(atividadeDoForm.getImagem());
                atividadeExistente.setUrlImagem(urlImagem);
            }

            atividadeRepository.save(atividadeExistente);
        }

        return "redirect:/admin";
    }

    @GetMapping("/apagar/{id}")
    public String confirmarExclusao(@PathVariable Long id, Model model) {
        Atividade atividade = atividadeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de atividade inválido:" + id));
        model.addAttribute("atividade", atividade);
        return "admin/confirmar-exclusao";
    }

    @PostMapping("/apagar/{id}")
    public String apagarAtividade(@PathVariable Long id) {
        atividadeRepository.deleteById(id);
        return "redirect:/admin";
    }
}