package com.computeca.bncc.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

   
    @ElementCollection
    @CollectionTable(name = "atividade_habilidades_bncc", joinColumns = @JoinColumn(name = "atividade_id"))
    @Column(name = "habilidade_bncc")
    private List<String> habilidadesBncc = new ArrayList<>();

    private String link;

    private String urlImagem;

    // Para upload de imagem no formulário
    @Transient
    private MultipartFile imagem;

    // Campos transient usados para edição no formulário
    @Transient
    private String habilidadesComoString;

    @Transient
    private String etapaComoString;

    // Construtor simplificado
    public Atividade(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // Popula os campos transient a partir dos dados persistidos
    public void prepararParaEdicao() {
        this.etapaComoString = this.etapaEducacional;
        if (this.habilidadesBncc != null && !this.habilidadesBncc.isEmpty()) {
            this.habilidadesComoString = String.join(", ", this.habilidadesBncc);
        }
    }

    // Atualiza os campos persistentes a partir dos campos do formulário
    public void atualizarAPartirDoFormulario() {
        this.etapaEducacional = this.etapaComoString;
        if (this.habilidadesComoString != null && !this.habilidadesComoString.isBlank()) {
            this.habilidadesBncc = List.of(this.habilidadesComoString.split("\\s*,\\s*"));
        } else {
            this.habilidadesBncc = new ArrayList<>();
        }
    }
}
