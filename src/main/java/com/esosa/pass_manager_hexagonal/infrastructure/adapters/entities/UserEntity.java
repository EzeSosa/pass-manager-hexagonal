package com.esosa.pass_manager_hexagonal.infrastructure.adapters.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity(name = "user")
public class UserEntity {

    @Id
    private UUID id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<PasswordEntity> passwords;

    public UserEntity(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserEntity() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PasswordEntity> getPasswords() {
        return passwords;
    }

    public void setPasswords(List<PasswordEntity> passwords) {
        this.passwords = passwords;
    }

}