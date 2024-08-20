package com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "password")
public class PasswordEntity {

    @Id
    private UUID id;
    private String name;
    private String password;
    private LocalDate createdAt;

    @ManyToOne
    private UserEntity user;

    public PasswordEntity(UUID id, String name, String password, LocalDate createdAt, UserEntity user) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.user = user;
    }

    public PasswordEntity() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}