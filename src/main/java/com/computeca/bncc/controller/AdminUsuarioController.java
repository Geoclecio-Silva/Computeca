package com.computeca.bncc.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.computeca.bncc.model.Role;
import com.computeca.bncc.model.User;
import com.computeca.bncc.repository.RoleRepository;
import com.computeca.bncc.repository.UserRepository; // ← IMPORT ADICIONADO
import com.computeca.bncc.service.UserService;

@Controller
@RequestMapping("/admin/usuarios")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminUsuarioController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository; // ← ADICIONADO

    // Listar todos os usuários (APENAS UM MÉTODO!)
    @GetMapping
    public String listarUsuarios(Model model) {
        // Opção 1: Apenas ativos (como estava antes)
        List<User> usuarios = userService.getAllActiveUsers();
        
        // Opção 2: Todos (inclusive inativos) - descomente se preferir
        // List<User> usuarios = userRepository.findAll();
        
        List<Role> todasRoles = roleRepository.findAll();
        
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("todasRoles", todasRoles);
        return "admin/lista-usuarios";
    }

    // Formulário de edição de roles
    @GetMapping("/editar/{id}")
    public String editarUsuarioForm(@PathVariable Long id, Model model) {
        User usuario = userService.getAllActiveUsers().stream()
            .filter(u -> u.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        List<Role> todasRoles = roleRepository.findAll();
        
        model.addAttribute("usuario", usuario);
        model.addAttribute("todasRoles", todasRoles);
        model.addAttribute("rolesDoUsuario", 
            usuario.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
        
        return "admin/editar-usuario";
    }

    // Salvar alterações de roles
    @PostMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id,
                               @RequestParam(required = false) List<String> roles,
                               RedirectAttributes redirectAttributes) {
        
        try {
            Set<String> roleSet = roles != null ? 
                Set.copyOf(roles) : 
                Set.of("ROLE_USER");
            
            userService.updateUserRoles(id, roleSet);
            redirectAttributes.addFlashAttribute("sucesso", "Permissões atualizadas com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar permissões: " + e.getMessage());
        }
        
        return "redirect:/admin/usuarios";
    }

    // Desativar usuário
    @PostMapping("/desativar/{id}")
    public String desativarUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deactivateUser(id);
            redirectAttributes.addFlashAttribute("sucesso", "Usuário desativado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao desativar usuário: " + e.getMessage());
        }
        
        return "redirect:/admin/usuarios";
    }

    // Promover usuário (adicionar role)
    @PostMapping("/promover/{id}")
    public String promoverUsuario(
            @PathVariable Long id,
            @RequestParam String role,
            RedirectAttributes redirectAttributes) {
        
        try {
            userService.promoverUsuario(id, role);
            redirectAttributes.addFlashAttribute("sucesso", 
                "Usuário promovido para " + role.replace("ROLE_", ""));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        
        return "redirect:/admin/usuarios";
    }

    // Rebaixar usuário (remover role)
    @PostMapping("/rebaixar/{id}")
    public String rebaixarUsuario(
            @PathVariable Long id,
            @RequestParam String role,
            RedirectAttributes redirectAttributes) {
        
        try {
            userService.rebaixarUsuario(id, role);
            redirectAttributes.addFlashAttribute("sucesso", 
                "Role " + role.replace("ROLE_", "") + " removida");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        
        return "redirect:/admin/usuarios";
    }
}