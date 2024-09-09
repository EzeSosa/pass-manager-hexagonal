package com.esosa.pass_manager_hexagonal.domain.ports.input.user;

import com.esosa.pass_manager_hexagonal.domain.model.User;

public interface SaveUserUseCase {
    void saveUser(User user);
}