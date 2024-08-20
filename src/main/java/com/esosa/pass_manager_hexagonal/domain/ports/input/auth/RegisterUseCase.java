package com.esosa.pass_manager_hexagonal.domain.ports.input.auth;

import com.esosa.pass_manager_hexagonal.domain.model.User;

public interface RegisterUseCase {
    void register(User user);
}