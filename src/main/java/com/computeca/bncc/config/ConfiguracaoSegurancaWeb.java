package com.computeca.bncc.config;

import com.computeca.bncc.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSegurancaWeb {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorize) -> authorize
                // Páginas públicas - INCLUÍDO /registro
                .requestMatchers("/", "/sobre", "/doacoes", "/registro", "/css/**", "/js/**", 
                               "/images/**", "/videos/**", "/buscar").permitAll()
                
                // Área administrativa - apenas ADMIN
                .requestMatchers("/admin/**").hasRole("ADMIN")
                
                // Revisão de atividades - ADMIN e CURADOR
                .requestMatchers("/atividades/revisao/**").hasAnyRole("ADMIN", "CURADOR")
                
                // Criação de atividades - ADMIN, CURADOR e COLABORADOR
                .requestMatchers("/atividades/nova", "/atividades/minhas").hasAnyRole("ADMIN", "CURADOR", "COLABORADOR")
                
                // Qualquer outra requisição precisa de autenticação
                .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .defaultSuccessUrl("/admin", true)  // ADMIN vai para /admin
                .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .permitAll()
            );
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}