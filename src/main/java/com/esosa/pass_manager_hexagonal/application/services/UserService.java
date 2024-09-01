package com.esosa.pass_manager_hexagonal.application.services;

import com.esosa.pass_manager_hexagonal.application.dtos.responses.PasswordResponse;
import com.esosa.pass_manager_hexagonal.application.mappers.IPasswordMapper;
import com.esosa.pass_manager_hexagonal.domain.shared.CustomPage;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.password.GetUserPasswordsUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.GetUserUseCase;

import java.util.UUID;

public class UserService {

    private final GetUserUseCase getUserUseCase;
    private final GetUserPasswordsUseCase getUserPasswordsUseCase;
    private final IPasswordMapper passwordMapper;

    public UserService(GetUserUseCase getUserUseCase, GetUserPasswordsUseCase getUserPasswordsUseCase, IPasswordMapper passwordMapper) {
        this.getUserUseCase = getUserUseCase;
        this.getUserPasswordsUseCase = getUserPasswordsUseCase;
        this.passwordMapper = passwordMapper;
    }

    public CustomPage<PasswordResponse> getUserPasswords(UUID userId, int pageNumber, int pageSize, String sortAttribute) {
        User _user = getUserUseCase.getUserById(userId);
        return getUserPasswordsUseCase.getUserPasswords(_user, pageNumber, pageSize, sortAttribute)
                .map(passwordMapper::toPasswordResponse);
    }

}