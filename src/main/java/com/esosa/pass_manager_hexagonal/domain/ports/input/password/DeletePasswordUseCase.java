package com.esosa.pass_manager_hexagonal.domain.ports.input.password;

import java.util.UUID;

public interface DeletePasswordUseCase {
    void deletePassword(UUID passwordId);
}