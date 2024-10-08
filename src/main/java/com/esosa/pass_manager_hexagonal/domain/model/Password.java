package com.esosa.pass_manager_hexagonal.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public class Password {

    private UUID id = UUID.randomUUID();
    private String name;
    private String password;
    private LocalDate createdAt = LocalDate.now();
    private User user;

    public Password(UUID id, String name, String password, LocalDate createdAt, User user) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.user = user;
    }

    public Password() {}

    public UUID getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}