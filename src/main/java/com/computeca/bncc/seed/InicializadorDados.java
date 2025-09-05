package com.computeca.bncc.seed;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.computeca.bncc.model.Atividade;
import com.computeca.bncc.model.Categoria;
import com.computeca.bncc.model.Habilidade;
import com.computeca.bncc.repository.AtividadeRepository;
import com.computeca.bncc.repository.HabilidadeRepository;

@Configuration
public class InicializadorDados {

    @Bean
    @Profile("dev")
    public CommandLineRunner carregarDados(AtividadeRepository repositorio, HabilidadeRepository habilidadeRepository) {
        return args -> {
            // Habilidades de exemplo
            Habilidade habilidade1 = new Habilidade("Ensino Fundamental I", "EF01MA01", "Contar e comparar números");
            Habilidade habilidade2 = new Habilidade("Ensino Fundamental I", "EF01MA02", "Resolver problemas com operações básicas");
            Habilidade habilidade3 = new Habilidade("Ensino Fundamental II", "EF06MA01", "Sistema de numeração decimal");
            Habilidade habilidade4 = new Habilidade("Ensino Fundamental II", "EF06MA02", "Operações com números naturais");
            
            habilidadeRepository.saveAll(Arrays.asList(habilidade1, habilidade2, habilidade3, habilidade4));

            // Atividades de exemplo
            Atividade atividade1 = new Atividade();
            atividade1.setNome("Introdução à Programação com Scratch");
            atividade1.setDescricao("Os alunos aprenderão conceitos básicos de programação como sequências, loops e condicionais, criando um pequeno jogo ou animação.");
            atividade1.setCategoria(Categoria.PROGRAMACAO);
            atividade1.setEtapaEducacional("Ensino Fundamental I");
            atividade1.setHabilidadesBncc(Arrays.asList(habilidade1, habilidade2)); // AGORA É List<Habilidade>
            atividade1.setLink("https://scratch.mit.edu/");
            atividade1.setUrlImagem("https://placehold.co/600x400.png");

            Atividade atividade2 = new Atividade();
            atividade2.setNome("Robótica com LEGO Education");
            atividade2.setDescricao("Atividade prática onde os alunos constroem e programam robôs simples para resolver desafios.");
            atividade2.setCategoria(Categoria.ROBOTICA);
            atividade2.setEtapaEducacional("Ensino Fundamental II");
            atividade2.setHabilidadesBncc(Arrays.asList(habilidade3, habilidade4)); // AGORA É List<Habilidade>
            atividade2.setLink("https://education.lego.com/");
            atividade2.setUrlImagem("https://placehold.co/600x400.png");

            // Salva as atividades no banco de dados
            repositorio.saveAll(Arrays.asList(atividade1, atividade2));
        };
    }
}