package com.computeca.bncc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "habilidades")
public class Habilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String etapaEducacional;

    private String codigoHabilidade;

    @Column(columnDefinition = "TEXT")
    private String descricao;
    

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtapaEducacional() {
        return etapaEducacional;
    }

    public void setEtapaEducacional(String etapaEducacional) {
        this.etapaEducacional = etapaEducacional;
    }

    public String getCodigoHabilidade() {
        return codigoHabilidade;
    }

    public void setCodigoHabilidade(String codigoHabilidade) {
        this.codigoHabilidade = codigoHabilidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}