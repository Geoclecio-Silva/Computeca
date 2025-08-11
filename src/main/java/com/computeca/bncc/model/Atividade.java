package com.computeca.bncc.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Enumerated(EnumType.STRING) // Anotação para persistir o enum como String
    private Categoria categoria;

    private String etapaEducacional;

    @ElementCollection
    private List<String> habilidadesBncc;

    private String link;

    private String urlImagem;

    @Transient
    private MultipartFile imagem;


    public Atividade(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}