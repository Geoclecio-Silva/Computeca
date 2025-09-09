package com.computeca.bncc.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.computeca.bncc.model.Atividade;
import com.computeca.bncc.model.Categoria;
import com.computeca.bncc.model.Habilidade;
import com.computeca.bncc.repository.AtividadeRepository;
import com.computeca.bncc.util.ThymeleafUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PaginaInicialController {

    @Autowired
    private AtividadeRepository atividadeRepository;
    
    private final int pageSize = 20;

    @Autowired
    private ThymeleafUtil thymeleafUtil;

    @GetMapping("/")
    public String paginaInicial(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String etapa,
            @RequestParam(required = false) String habilidade,
            @RequestParam(defaultValue = "0") int page,
            Model model,
            HttpServletRequest request
    ) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Categoria categoriaEnum = (categoria != null && !categoria.isEmpty()) ? Categoria.valueOf(categoria) : null;

        Page<Atividade> atividadesPage = atividadeRepository.buscarComFiltros(nome, categoriaEnum, etapa, habilidade, pageable);
        
        List<Atividade> atividades = atividadesPage.getContent();
        
        // NOVO CÓDIGO: Popula a classe de estilo para cada habilidade
        for (Atividade atividade : atividades) {
            if (atividade.getHabilidadesBncc() != null) {
                for (Habilidade hab : atividade.getHabilidadesBncc()) {
                    hab.popularEtapaClasse();
                }
            }
        }
        
        model.addAttribute("atividades", atividades);
        
        model.addAttribute("categorias", Arrays.asList(Categoria.values()));
        model.addAttribute("etapas", Arrays.asList("Educação Infantil", "Ensino Fundamental I", "Ensino Fundamental II", "Ensino Médio"));
        model.addAttribute("thymeleafUtil", thymeleafUtil);

        model.addAttribute("servletPath", request.getServletPath());
        
        model.addAttribute("hasMore", atividadesPage.hasNext());
        
        // Adiciona os parâmetros de busca ao modelo para que o formulário mantenha o estado
        model.addAttribute("nomeParam", nome);
        model.addAttribute("categoriaParam", categoria);
        model.addAttribute("etapaParam", etapa);
        model.addAttribute("habilidadeParam", habilidade);
                
        return "pagina-inicial";
    }
    
    @GetMapping("/buscar")
    public String buscarAtividades(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String etapa,
            @RequestParam(required = false) String habilidade,
            @RequestParam(defaultValue = "0") int page,
            Model model
    ) {
        Pageable pageable = PageRequest.of(page, pageSize);

        Categoria categoriaEnum = (categoria != null && !categoria.isEmpty()) ? Categoria.valueOf(categoria) : null;

        Page<Atividade> atividadesPage = atividadeRepository.buscarComFiltros(nome, categoriaEnum, etapa, habilidade, pageable);

        List<Atividade> atividades = atividadesPage.getContent();

        // NOVO CÓDIGO: Popula a classe de estilo para cada habilidade
        for (Atividade atividade : atividades) {
            if (atividade.getHabilidadesBncc() != null) {
                for (Habilidade hab : atividade.getHabilidadesBncc()) {
                    hab.popularEtapaClasse();
                }
            }
        }

        model.addAttribute("atividades", atividades);
        model.addAttribute("hasMore", atividadesPage.hasNext());

        return "fragments/resultados :: resultados";
    }
    
    @GetMapping("/sobre")
    public String exibirPaginaSobre() {
        return "sobre";
    }
}
