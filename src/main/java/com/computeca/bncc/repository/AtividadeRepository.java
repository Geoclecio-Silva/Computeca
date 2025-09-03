package com.computeca.bncc.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.computeca.bncc.model.Atividade;
import com.computeca.bncc.model.Categoria;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    @Query("SELECT DISTINCT a.categoria FROM Atividade a")
    List<String> findDistinctCategorias();

    @Query("SELECT DISTINCT a.etapaEducacional FROM Atividade a")
    List<String> findDistinctEtapasEducacionais();

    @Query(value = "SELECT DISTINCT a FROM Atividade a LEFT JOIN FETCH a.habilidadesBncc h WHERE " +
                    "(:nome is null or a.nome LIKE CONCAT('%', :nome, '%')) and " +
                    "(:categoria is null or a.categoria = :categoria) and " +
                    "(:etapa is null or a.etapaEducacional = :etapa) and " +
                    "(:habilidade is null or :habilidade = '' or h LIKE CONCAT('%', :habilidade, '%'))",
           countQuery = "SELECT COUNT(DISTINCT a) FROM Atividade a LEFT JOIN a.habilidadesBncc h WHERE " +
                         "(:nome is null or a.nome LIKE CONCAT('%', :nome, '%')) and " +
                         "(:categoria is null or a.categoria = :categoria) and " +
                         "(:etapa is null or a.etapaEducacional = :etapa) and " +
                         "(:habilidade is null or :habilidade = '' or h LIKE CONCAT('%', :habilidade, '%'))"
    )
    Page<Atividade> buscarComFiltros(
        @Param("nome") String nome,
        @Param("categoria") Categoria categoria,
        @Param("etapa") String etapa,
        @Param("habilidade") String habilidade,
        Pageable pageable
    );
}