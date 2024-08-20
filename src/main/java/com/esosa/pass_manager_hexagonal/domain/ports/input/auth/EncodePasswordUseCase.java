package com.esosa.pass_manager_hexagonal.domain.ports.input.auth;

public interface EncodePasswordUseCase {
    String encodePassword(String rawPassword);
}