package com.computeca.bncc.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "habilidades")
public class Habilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campo temporário para classe CSS no Thymeleaf
    @Transient
    private String etapaClasse;

    @NotBlank(message = "A etapa educacional é obrigatória")
    private String etapaEducacional;

    @NotBlank(message = "O código da habilidade é obrigatório")
    private String codigoHabilidade;

    @NotBlank(message = "A descrição é obrigatória")
    @Column(columnDefinition = "TEXT")
    private String descricao;

    public Habilidade() {}

    public Habilidade(String etapaEducacional, String codigoHabilidade, String descricao) {
        this.etapaEducacional = etapaEducacional;
        this.codigoHabilidade = codigoHabilidade;
        this.descricao = descricao;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEtapaClasse() { return etapaClasse; }
    public void setEtapaClasse(String etapaClasse) { this.etapaClasse = etapaClasse; }

    public String getEtapaEducacional() { return etapaEducacional; }
    public void setEtapaEducacional(String etapaEducacional) { this.etapaEducacional = etapaEducacional; }

    public String getCodigoHabilidade() { return codigoHabilidade; }
    public void setCodigoHabilidade(String codigoHabilidade) { this.codigoHabilidade = codigoHabilidade; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return codigoHabilidade + " - " + descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habilidade that = (Habilidade) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
