package com.esosa.pass_manager_hexagonal.domain.ports.output.auth;

public interface PasswordEncodingPort {
    String encodePassword(String rawPassword);
}