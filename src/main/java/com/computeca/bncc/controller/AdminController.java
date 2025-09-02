package com.computeca.bncc.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping
    public String listarAtividades(Model model) {
        model.addAttribute("atividades", atividadeRepository.findAll());
        return "admin/lista-atividades";
    }

    @GetMapping("/cadastro")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("atividade", new Atividade());
        model.addAttribute("todasHabilidades", getTodasHabilidadesFormatadas());
        return "admin/formulario-atividade";
    }

    @PostMapping("/cadastro")
    public String cadastrarAtividade(Atividade atividade, 
                                   @RequestParam(value = "habilidadesBncc", required = false) List<String> habilidadesSelecionadas) throws IOException {
        
        // Define as habilidades selecionadas (códigos)
        atividade.setHabilidadesBncc(habilidadesSelecionadas != null ? habilidadesSelecionadas : List.of());

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
        
        model.addAttribute("atividade", atividade);
        model.addAttribute("todasHabilidades", getTodasHabilidadesFormatadas());
        return "admin/formulario-atividade";
    }
   
    @PostMapping("/editar/{id}")
    public String editarAtividade(@PathVariable Long id, 
                                Atividade atividadeAtualizada,
                                @RequestParam(value = "habilidadesBncc", required = false) List<String> habilidadesSelecionadas) throws IOException {
        
        Atividade atividadeExistente = atividadeRepository.findById(id)
                                                   .orElseThrow(() -> new IllegalArgumentException("ID de atividade inválido:" + id));

        // Atualiza as habilidades (códigos)
        atividadeExistente.setHabilidadesBncc(habilidadesSelecionadas != null ? habilidadesSelecionadas : List.of());

        // Atualiza os outros campos
        atividadeExistente.setNome(atividadeAtualizada.getNome());
        atividadeExistente.setDescricao(atividadeAtualizada.getDescricao());
        atividadeExistente.setCategoria(atividadeAtualizada.getCategoria());
        atividadeExistente.setEtapaEducacional(atividadeAtualizada.getEtapaEducacional());
        atividadeExistente.setLink(atividadeAtualizada.getLink());

        // Lógica de imagem
        if (atividadeAtualizada.getImagem() != null && !atividadeAtualizada.getImagem().isEmpty()) {
            String urlImagem = servicoImagem.salvarImagem(atividadeAtualizada.getImagem());
            atividadeExistente.setUrlImagem(urlImagem);
        }

        atividadeRepository.save(atividadeExistente);
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

    // Método auxiliar para buscar todas as habilidades formatadas
    private List<String> getTodasHabilidadesFormatadas() {
        return habilidadeRepository.findAll().stream()
                .map(h -> h.getCodigoHabilidade() + " - " + h.getDescricao())
                .collect(Collectors.toList());
    }

    // Método auxiliar para buscar apenas os códigos das habilidades
    private List<String> getTodosCodigosHabilidades() {
        return habilidadeRepository.findAll().stream()
                .map(Habilidade::getCodigoHabilidade)
                .collect(Collectors.toList());
    }
}