package com.computeca.bncc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "atividades")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(length = 2000)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private String etapaEducacional;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "atividade_habilidades",
        joinColumns = @JoinColumn(name = "atividade_id"),
        inverseJoinColumns = @JoinColumn(name = "habilidade_id")
    )
    private List<Habilidade> habilidadesBncc = new ArrayList<>();

    private String link;

    private String urlImagem;

    @Transient
    private MultipartFile imagem;

    @Transient
    private String habilidadesComoString;

    @Transient
    private String etapaComoString;

  
    public Atividade(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // Prepara campos auxiliares para edição no formulário
    public void prepararParaEdicao() {
        this.etapaComoString = this.etapaEducacional;
        if (this.habilidadesBncc != null && !this.habilidadesBncc.isEmpty()) {
            this.habilidadesComoString = this.habilidadesBncc.stream()
                .map(h -> h.getId().toString())
                .collect(Collectors.joining(", "));
        }
    }

    // Atualiza campos a partir do formulário
    public void atualizarAPartirDoFormulario() {
        this.etapaEducacional = this.etapaComoString;
      
    }
}
