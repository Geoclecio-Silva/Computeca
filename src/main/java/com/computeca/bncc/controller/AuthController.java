package com.computeca.bncc.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.computeca.bncc.model.User;
import com.computeca.bncc.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new User());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String nome,
            RedirectAttributes redirectAttributes) {
        
        try {
            // Sempre cria como ROLE_USER (usuário comum)
            Set<String> roles = Set.of("ROLE_USER");
            
            userService.createUser(email, password, nome, roles);
            
            redirectAttributes.addFlashAttribute("sucesso", 
                "Cadastro realizado com sucesso! Faça login para continuar.");
            
            return "redirect:/login?registered";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", 
                "Erro ao cadastrar: " + e.getMessage());
            return "redirect:/registro";
        }
    }
}