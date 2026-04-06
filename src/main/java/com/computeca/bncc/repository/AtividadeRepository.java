package com.computeca.bncc.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.computeca.bncc.model.Atividade;
import com.computeca.bncc.model.Categoria;
import com.computeca.bncc.model.StatusAtividade;
import com.computeca.bncc.model.User;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    @Query("SELECT DISTINCT a.categoria FROM Atividade a")
    List<String> findDistinctCategorias();

    @Query("SELECT DISTINCT a.etapaEducacional FROM Atividade a")
    List<String> findDistinctEtapasEducacionais();

    // Query existente de busca com filtros
    @Query(value = "SELECT DISTINCT a FROM Atividade a " +
               "LEFT JOIN a.habilidadesBncc h " +
               "WHERE " +
               "(:nome is null or a.nome LIKE CONCAT('%', :nome, '%')) and " +
               "(:categoria is null or a.categoria = :categoria) and " +
               "(:etapa is null or a.etapaEducacional = :etapa) and " +
               "(:habilidade is null or :habilidade = '' or " +
               "    EXISTS (SELECT 1 FROM a.habilidadesBncc h2 WHERE " +
               "        h2.descricao LIKE CONCAT('%', :habilidade, '%') OR " +
               "        h2.codigoHabilidade LIKE CONCAT('%', :habilidade, '%')))",
       countQuery = "SELECT COUNT(DISTINCT a) FROM Atividade a " +
                    "WHERE " +
                    "(:nome is null or a.nome LIKE CONCAT('%', :nome, '%')) and " +
                    "(:categoria is null or a.categoria = :categoria) and " +
                    "(:etapa is null or a.etapaEducacional = :etapa) and " +
                    "(:habilidade is null or :habilidade = '' or " +
                    "    EXISTS (SELECT 1 FROM a.habilidadesBncc h WHERE " +
                    "        h.descricao LIKE CONCAT('%', :habilidade, '%') OR " +
                    "        h.codigoHabilidade LIKE CONCAT('%', :habilidade, '%')))")
    Page<Atividade> buscarComFiltros(
        @Param("nome") String nome,
        @Param("categoria") Categoria categoria,
        @Param("etapa") String etapa,
        @Param("habilidade") String habilidade,
        Pageable pageable
    );
    
    // ===== NOVOS MÉTODOS PARA O SISTEMA DE APROVAÇÃO =====
    
    // Buscar atividades por status (ex: PENDENTE)
    Page<Atividade> findByStatus(StatusAtividade status, Pageable pageable);
    
    // Buscar atividades criadas por um usuário específico
    Page<Atividade> findByCriadoPor(User criadoPor, Pageable pageable);
    
    // Buscar atividades por status E criador
    Page<Atividade> findByStatusAndCriadoPor(StatusAtividade status, User criadoPor, Pageable pageable);
    
    // Buscar atividades pendentes ordenadas das mais antigas para as mais novas
    @Query("SELECT a FROM Atividade a WHERE a.status = :status ORDER BY a.criadoEm ASC")
    Page<Atividade> findPendentesOrdenados(@Param("status") StatusAtividade status, Pageable pageable);
    
    // Buscar atividades aprovadas (para exibição pública)
    @Query("SELECT a FROM Atividade a WHERE a.status = 'APROVADA'")
    Page<Atividade> findAllAprovadas(Pageable pageable);
    
    // Buscar atividades pendentes de um usuário específico
    @Query("SELECT a FROM Atividade a WHERE a.status = :status AND a.criadoPor = :criadoPor")
    Page<Atividade> findMinhasPendentes(@Param("status") StatusAtividade status, 
                                        @Param("criadoPor") User criadoPor, 
                                        Pageable pageable);
    
    // Contar atividades por status (útil para dashboard)
    long countByStatus(StatusAtividade status);
    
    // Contar atividades de um usuário por status
    long countByStatusAndCriadoPor(StatusAtividade status, User criadoPor);

    @Query(value = "SELECT DISTINCT a FROM Atividade a " +
       "LEFT JOIN a.habilidadesBncc h " +
       "WHERE a.status = 'APROVADA' AND " +  // ← FILTRO CRÍTICO!
       "(:nome is null or a.nome LIKE CONCAT('%', :nome, '%')) and " +
       "(:categoria is null or a.categoria = :categoria) and " +
       "(:etapa is null or a.etapaEducacional = :etapa) and " +
       "(:habilidade is null or :habilidade = '' or " +
       "    EXISTS (SELECT 1 FROM a.habilidadesBncc h2 WHERE " +
       "        h2.descricao LIKE CONCAT('%', :habilidade, '%') OR " +
       "        h2.codigoHabilidade LIKE CONCAT('%', :habilidade, '%')))",
   countQuery = "SELECT COUNT(DISTINCT a) FROM Atividade a " +
                "WHERE a.status = 'APROVADA' AND " +
                "(:nome is null or a.nome LIKE CONCAT('%', :nome, '%')) and " +
                "(:categoria is null or a.categoria = :categoria) and " +
                "(:etapa is null or a.etapaEducacional = :etapa) and " +
                "(:habilidade is null or :habilidade = '' or " +
                "    EXISTS (SELECT 1 FROM a.habilidadesBncc h WHERE " +
                "        h.descricao LIKE CONCAT('%', :habilidade, '%') OR " +
                "        h.codigoHabilidade LIKE CONCAT('%', :habilidade, '%')))")
Page<Atividade> buscarAtividadesPublicas(
    @Param("nome") String nome,
    @Param("categoria") Categoria categoria,
    @Param("etapa") String etapa,
    @Param("habilidade") String habilidade,
    Pageable pageable
);
}