package com.computeca.bncc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.computeca.bncc.model.Habilidade;

@Repository
public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {
}