package com.computeca.bncc.controller;

import com.computeca.bncc.model.Atividade;
import com.computeca.bncc.model.StatusAtividade;
import com.computeca.bncc.model.User; // ← VERIFIQUE SE ESTE IMPORT ESTÁ CORRETO!
import com.computeca.bncc.service.AtividadeService;
import com.computeca.bncc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/atividades")
public class AtividadeController {
    
    @Autowired
    private AtividadeService atividadeService;
    
    @Autowired
    private UserService userService;
    
    // Colaborador e Curador podem acessar
    @GetMapping("/nova")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CURADOR', 'ROLE_COLABORADOR')")
    public String novaAtividadeForm(Model model) {
        model.addAttribute("atividade", new Atividade());
        return "atividades/nova";
    }
    
    @PostMapping("/nova")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CURADOR', 'ROLE_COLABORADOR')")
    public String criarAtividade(@ModelAttribute Atividade atividade,
                                @AuthenticationPrincipal org.springframework.security.core.userdetails.User springUser, // ← MUDANÇA AQUI
                                RedirectAttributes redirectAttributes) {
        try {
            // Precisamos buscar o User completo do banco
            User usuario = userService.findByEmail(springUser.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            
            atividadeService.criarAtividade(atividade, usuario);
            redirectAttributes.addFlashAttribute("sucesso", "Atividade criada com sucesso!");
            
            if (usuario.hasRole("ROLE_COLABORADOR")) {
                redirectAttributes.addFlashAttribute("info", 
                    "Sua atividade será revisada por um curador antes de ser publicada.");
            }
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao criar atividade: " + e.getMessage());
        }
        
        return "redirect:/atividades/minhas";
    }
    
    // Apenas Curador e Admin
    @GetMapping("/revisao")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CURADOR')")
    public String atividadesParaRevisao(@AuthenticationPrincipal org.springframework.security.core.userdetails.User springUser,
                                       Pageable pageable,
                                       Model model) {
        User usuario = userService.findByEmail(springUser.getUsername())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        Page<Atividade> atividades = atividadeService.getAtividadesParaRevisao(usuario, pageable);
        model.addAttribute("atividades", atividades);
        return "atividades/revisao";
    }
    
    @PostMapping("/revisar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CURADOR')")
    public String revisarAtividade(@PathVariable Long id,
                                  @RequestParam StatusAtividade status,
                                  @RequestParam(required = false) String motivo,
                                  @AuthenticationPrincipal org.springframework.security.core.userdetails.User springUser,
                                  RedirectAttributes redirectAttributes) {
        try {
            User revisor = userService.findByEmail(springUser.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            
            atividadeService.revisarAtividade(id, status, revisor, motivo);
            
            if (status == StatusAtividade.APROVADA) {
                redirectAttributes.addFlashAttribute("sucesso", "Atividade aprovada e publicada!");
            } else if (status == StatusAtividade.REJEITADA) {
                redirectAttributes.addFlashAttribute("aviso", "Atividade rejeitada. Motivo enviado ao colaborador.");
            }
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao revisar atividade: " + e.getMessage());
        }
        
        return "redirect:/atividades/revisao";
    }
    
    // Minhas atividades
    @GetMapping("/minhas")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CURADOR', 'ROLE_COLABORADOR')")
    public String minhasAtividades(@AuthenticationPrincipal org.springframework.security.core.userdetails.User springUser,
                                  Pageable pageable,
                                  Model model) {
        User usuario = userService.findByEmail(springUser.getUsername())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        Page<Atividade> atividades = atividadeService.getMinhasAtividades(usuario, pageable);
        model.addAttribute("atividades", atividades);
        return "atividades/minhas";
    }
    
    // Editar atividade
    @GetMapping("/editar/{id}")
    public String editarAtividadeForm(@PathVariable Long id,
                                     @AuthenticationPrincipal org.springframework.security.core.userdetails.User springUser,
                                     Model model) {
        User usuario = userService.findByEmail(springUser.getUsername())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        // Verifica permissão dentro do service
        Atividade atividade = atividadeService.editarAtividade(id, new Atividade(), usuario);
        model.addAttribute("atividade", atividade);
        return "atividades/editar";
    }
}