package com.esosa.pass_manager_hexagonal.application.services;

import com.esosa.pass_manager_hexagonal.application.dtos.responses.PasswordResponse;
import com.esosa.pass_manager_hexagonal.application.mappers.PasswordMapper;
import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.password.GetUserPasswordsUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.GetUserUseCase;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserService {

    private final GetUserUseCase getUserUseCase;
    private final GetUserPasswordsUseCase getUserPasswordsUseCase;

    public UserService(GetUserUseCase getUserUseCase, GetUserPasswordsUseCase getUserPasswordsUseCase) {
        this.getUserUseCase = getUserUseCase;
        this.getUserPasswordsUseCase = getUserPasswordsUseCase;
    }

    public List<PasswordResponse> getUserPasswords(UUID userId) {
        User _user = getUserUseCase.getUserById(userId);
        List<Password> _userPasswords = getUserPasswordsUseCase.getUserPasswords(_user);
        return _userPasswords.stream()
                .map(PasswordMapper::toPasswordResponse)
                .collect(Collectors.toList());
    }

}