package com.esosa.pass_manager_hexagonal.domain.model;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class User {

    private UUID id = UUID.randomUUID();
    private String username;
    private String password;
    private List<Password> passwords = Collections.emptyList();

    public User(UUID id, String username, String password, List<Password> passwords) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.passwords = passwords;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {}

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

    public List<Password> getPasswords() {
        return passwords;
    }

    public void setPasswords(List<Password> passwords) {
        this.passwords = passwords;
    }

}