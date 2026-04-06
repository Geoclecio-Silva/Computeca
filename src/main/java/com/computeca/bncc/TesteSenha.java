package com.computeca.bncc;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TesteSenha {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        String senhaDigitada = "admin123";
        String senhaBanco = "$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.";
        
        boolean matches = encoder.matches(senhaDigitada, senhaBanco);
        System.out.println("=== TESTE DE SENHA ===");
        System.out.println("Senha digitada: " + senhaDigitada);
        System.out.println("Hash no banco: " + senhaBanco);
        System.out.println("Correspondem? " + (matches ? "✅ SIM" : "❌ NÃO"));
        
        // Teste adicional - gerar novo hash
        String novaSenha = encoder.encode("admin123");
        System.out.println("\nNovo hash para 'admin123': " + novaSenha);
    }
}