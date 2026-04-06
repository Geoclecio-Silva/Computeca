package com.computeca.bncc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DoacoesController {
    
    @GetMapping("/doacoes")
    public String exibirPaginaDoacoes(Model model, HttpServletRequest request) {
        model.addAttribute("servletPath", request.getServletPath());
        return "doacoes";
    }
}