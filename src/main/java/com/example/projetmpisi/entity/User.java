package com.example.projetmpisi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String email;

    // ✅ Constructeur vide (OBLIGATOIRE pour JPA)
    public User() {
    }

    // ✅ Constructeur avec paramètres (POUR LES TESTS)
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
