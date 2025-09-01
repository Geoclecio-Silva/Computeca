package com.computeca.bncc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String editarAtividade(@PathVariable Long id, Atividade atividade) throws IOException {
        Atividade atividadeExistente = atividadeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de atividade inválido:" + id));

        // 1. Lógica de habilidades BNCC
        //    Atualiza SOMENTE se o campo foi preenchido no formulário.
        if (atividade.getHabilidadesBncc() != null && !atividade.getHabilidadesBncc().isEmpty()) {
            List<String> habilidades = Arrays.stream(atividade.getHabilidadesBncc().get(0).split(","))
                .map(String::trim)
                .collect(Collectors.toList());
            atividadeExistente.setHabilidadesBncc(habilidades);
        }
        // Não precisa de um `else`. Se o campo não veio, o valor existente é mantido.

        // 2. Lógica de imagem
        if (atividade.getImagem() != null && !atividade.getImagem().isEmpty()) {
            String urlImagem = servicoImagem.salvarImagem(atividade.getImagem());
            atividadeExistente.setUrlImagem(urlImagem);
        }

        // 3. Atualiza os outros campos
        //    É preciso checar se os outros campos que não são obrigatórios são nulos
        //    Se a etapa educacional for um enum ou String, essa verificação é crucial
        if (atividade.getEtapaEducacional() != null) {
        atividadeExistente.setEtapaEducacional(atividade.getEtapaEducacional());
        }

        // Atualiza os outros campos que não são listas ou arquivos.
        // Esses campos provavelmente não precisam de checagem de nulo
        // se eles sempre são enviados pelo formulário.
        atividadeExistente.setNome(atividade.getNome());
        atividadeExistente.setDescricao(atividade.getDescricao());
        atividadeExistente.setCategoria(atividade.getCategoria());
        atividadeExistente.setLink(atividade.getLink());

        // 4. Salva o objeto atualizado no repositório
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
}