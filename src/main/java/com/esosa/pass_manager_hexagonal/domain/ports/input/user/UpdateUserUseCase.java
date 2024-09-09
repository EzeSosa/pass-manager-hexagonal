package com.esosa.pass_manager_hexagonal.domain.ports.input.user;

import com.esosa.pass_manager_hexagonal.domain.model.User;

import java.util.UUID;

public interface UpdateUserUseCase {
    User updateUser(UUID userId, User user);
}