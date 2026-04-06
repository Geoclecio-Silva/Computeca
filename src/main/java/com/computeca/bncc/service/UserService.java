package com.computeca.bncc.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computeca.bncc.model.Role;
import com.computeca.bncc.model.User;
import com.computeca.bncc.repository.RoleRepository;
import com.computeca.bncc.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Transactional
    public User createUser(String email, String password, String nome, Set<String> roleNames) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email já cadastrado");
        }
        
        User user = new User(email, passwordEncoder.encode(password), nome);
        
        // Se não veio nenhuma role, coloca ROLE_USER como padrão
        if (roleNames == null || roleNames.isEmpty()) {
            roleNames = Set.of("ROLE_USER");
        }
        
        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role não encontrada: " + roleName));
            user.addRole(role);
        }
        
        return userRepository.save(user);
    }
    
    @Transactional
    public User updateUserRoles(Long userId, Set<String> roleNames) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        user.getRoles().clear();
        
        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role não encontrada: " + roleName));
            user.addRole(role);
        }
        
        return userRepository.save(user);
    }
    
    public List<User> getAllActiveUsers() {
        return userRepository.findByIsActive(true);
    }
    
    public List<User> getUsersByRole(String roleName) {
        return userRepository.findByRoleName(roleName);
    }
    
    @Transactional
    public void deactivateUser(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.setActive(false);
        userRepository.save(user);
    }
    
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // ===== NOVOS MÉTODOS PARA PROMOÇÃO =====
    
    @Transactional
    public User promoverUsuario(Long userId, String novaRole) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        // Verificar se já tem a role
        if (user.hasRole(novaRole)) {
            throw new RuntimeException("Usuário já possui esta role");
        }
        
        // Verificar regras de negócio
        if (novaRole.equals("ROLE_CURADOR") && !user.hasRole("ROLE_COLABORADOR")) {
            throw new RuntimeException("Usuário precisa ser COLABORADOR primeiro");
        }
        
        Role role = roleRepository.findByName(novaRole)
            .orElseThrow(() -> new RuntimeException("Role não encontrada: " + novaRole));
        
        user.addRole(role);
        return userRepository.save(user);
    }
    
    @Transactional
    public User rebaixarUsuario(Long userId, String roleRemover) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        // Não permitir remover a própria role ADMIN
        if (user.hasRole("ROLE_ADMIN") && roleRemover.equals("ROLE_ADMIN")) {
            throw new RuntimeException("Não é possível remover role ADMIN");
        }
        
        // Não permitir remover a última role
        if (user.getRoles().size() <= 1) {
            throw new RuntimeException("Usuário deve ter pelo menos uma role");
        }
        
        user.getRoles().removeIf(role -> role.getName().equals(roleRemover));
        return userRepository.save(user);
    }
    
    // Métodos de verificação de permissão
    public boolean userCanCreateActivity(User user) {
        return user.hasRole("ROLE_ADMIN") || 
               user.hasRole("ROLE_CURADOR") || 
               user.hasRole("ROLE_COLABORADOR");
    }
    
    public boolean userCanReviewActivity(User user) {
        return user.hasRole("ROLE_ADMIN") || user.hasRole("ROLE_CURADOR");
    }
    
    public boolean userCanEditActivity(User user, Long atividadeId) {
        if (user.hasRole("ROLE_ADMIN")) {
            return true;
        }
        
        if (user.hasRole("ROLE_CURADOR")) {
            return true;
        }
        
        // Colaborador só pode editar suas próprias atividades pendentes
        // Implementar lógica específica depois
        return false;
    }
}