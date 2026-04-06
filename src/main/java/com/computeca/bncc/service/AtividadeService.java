package com.computeca.bncc.service;

import com.computeca.bncc.model.Atividade;
import com.computeca.bncc.model.StatusAtividade;
import com.computeca.bncc.model.User;
import com.computeca.bncc.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AtividadeService {
    
    @Autowired
    private AtividadeRepository atividadeRepository;
    
    @Autowired
    private UserService userService;
    
    @Transactional
    public Atividade criarAtividade(Atividade atividade, User usuario) {
        if (!userService.userCanCreateActivity(usuario)) {
            throw new RuntimeException("Usuário não tem permissão para criar atividades");
        }
        
        atividade.setCriadoPor(usuario);
        atividade.setCriadoEm(LocalDateTime.now());
        
        // Define status baseado no tipo de usuário
        if (usuario.hasRole("ROLE_COLABORADOR")) {
            atividade.setStatus(StatusAtividade.PENDENTE);
        } else if (usuario.hasRole("ROLE_CURADOR") || usuario.hasRole("ROLE_ADMIN")) {
            atividade.setStatus(StatusAtividade.APROVADA);
            atividade.setRevisadoPor(usuario);
            atividade.setRevisadoEm(LocalDateTime.now());
        }
        
        return atividadeRepository.save(atividade);
    }
    
    @Transactional
    public Atividade revisarAtividade(Long atividadeId, StatusAtividade novoStatus, 
                                     User revisor, String motivo) {
        Atividade atividade = atividadeRepository.findById(atividadeId)
            .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));
        
        if (!userService.userCanReviewActivity(revisor)) {
            throw new RuntimeException("Usuário não tem permissão para revisar atividades");
        }
        
        // Atualiza status
        atividade.setStatus(novoStatus);
        atividade.setRevisadoPor(revisor);
        atividade.setRevisadoEm(LocalDateTime.now());
        
        if (novoStatus == StatusAtividade.REJEITADA) {
            atividade.setMotivoRejeicao(motivo);
        }
        
        return atividadeRepository.save(atividade);
    }
    
    @Transactional
    public Atividade editarAtividade(Long atividadeId, Atividade dadosAtualizados, User editor) {
        Atividade atividade = atividadeRepository.findById(atividadeId)
            .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));
        
        if (!userService.userCanEditActivity(editor, atividadeId)) {
            throw new RuntimeException("Usuário não tem permissão para editar esta atividade");
        }
        
        // Atualiza campos
        atividade.setNome(dadosAtualizados.getNome());
        atividade.setDescricao(dadosAtualizados.getDescricao());
        // ... outros campos
        
        // Se for colaborador editando, volta para pendente
        if (editor.hasRole("ROLE_COLABORADOR")) {
            atividade.setStatus(StatusAtividade.PENDENTE);
        }
        
        return atividadeRepository.save(atividade);
    }
    
    public Page<Atividade> getAtividadesParaRevisao(User revisor, Pageable pageable) {
        if (!userService.userCanReviewActivity(revisor)) {
            throw new RuntimeException("Usuário não tem permissão para revisar atividades");
        }
        
        return atividadeRepository.findByStatus(StatusAtividade.PENDENTE, pageable);
    }
    
    public Page<Atividade> getMinhasAtividades(User usuario, Pageable pageable) {
        return atividadeRepository.findByCriadoPor(usuario, pageable);
    }
}