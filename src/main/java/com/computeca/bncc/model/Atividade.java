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

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private String etapaEducacional;

    @ElementCollection
    private List<String> habilidadesBncc;

    private String link;

    private String urlImagem;

    @Transient
    private MultipartFile imagem;

    // Campos auxiliares para exibir no formulário
    @Transient
    private String habilidadesComoString;

    @Transient
    private String etapaComoString;

    public void prepararParaEdicao() {
        // Converte a lista para string no formulário
        if (habilidadesBncc != null && !habilidadesBncc.isEmpty()) {
            this.habilidadesComoString = String.join(", ", habilidadesBncc);
        }
        // Copia a etapa para o transient (evita null no select)
        this.etapaComoString = this.etapaEducacional;
    }

    public void atualizarAPartirDoFormulario() {
        // Atualiza lista de habilidades a partir da string do form
        if (habilidadesComoString != null && !habilidadesComoString.isBlank()) {
            this.habilidadesBncc = List.of(habilidadesComoString.split("\\s*,\\s*"));
        }
        // Atualiza etapa
        if (etapaComoString != null && !etapaComoString.isBlank()) {
            this.etapaEducacional = etapaComoString;
        }
    }
}
