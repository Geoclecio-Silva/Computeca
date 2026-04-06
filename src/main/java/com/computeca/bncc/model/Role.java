package com.computeca.bncc.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 50)
    private String name;
    
    @Column(length = 200)
    private String description;
    
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
    
    // Construtores
    public Role() {}
    
    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Set<User> getUsers() { return users; }
    public void setUsers(Set<User> users) { this.users = users; }
}

// Enum para facilitar
enum RoleType {
    ROLE_ADMIN("Administrador"),
    ROLE_CURADOR("Curador"),
    ROLE_COLABORADOR("Colaborador"),
    ROLE_USER("Usuário");
    
    private final String description;
    
    RoleType(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}