package com.esosa.pass_manager_hexagonal.domain.ports.input.password;

import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;

import java.util.List;

public interface GetUserPasswordsUseCase {
    List<Password> getUserPasswords(User user);
}