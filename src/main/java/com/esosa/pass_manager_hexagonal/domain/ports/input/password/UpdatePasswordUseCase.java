package com.esosa.pass_manager_hexagonal.domain.ports.input.password;

import com.esosa.pass_manager_hexagonal.domain.model.Password;

import java.util.UUID;

public interface UpdatePasswordUseCase {
    Password updatePassword(UUID passwordId, Password password);
}