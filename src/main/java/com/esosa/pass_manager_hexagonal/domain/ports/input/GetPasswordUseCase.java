package com.esosa.pass_manager_hexagonal.domain.ports.input;

import com.esosa.pass_manager_hexagonal.domain.model.Password;

import java.util.UUID;

public interface GetPasswordUseCase {
    Password getPassword(UUID passwordId);
}