package com.esosa.pass_manager_hexagonal.domain.ports.input;

import java.util.UUID;

public interface DeletePasswordUseCase {
    void deletePassword(UUID passwordId);
}