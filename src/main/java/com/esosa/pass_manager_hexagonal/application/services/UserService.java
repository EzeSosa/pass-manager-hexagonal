package com.esosa.pass_manager_hexagonal.application.services;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.UserRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.PasswordResponse;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.UserResponse;
import com.esosa.pass_manager_hexagonal.application.mappers.IPasswordMapper;
import com.esosa.pass_manager_hexagonal.application.mappers.IUserMapper;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.UpdateUserUseCase;
import com.esosa.pass_manager_hexagonal.domain.shared.CustomPage;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.password.GetUserPasswordsUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.GetUserUseCase;

import java.util.UUID;

public class UserService {

    private final GetUserUseCase getUserUseCase;
    private final GetUserPasswordsUseCase getUserPasswordsUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final IPasswordMapper passwordMapper;
    private final IUserMapper userMapper;

    public UserService(GetUserUseCase getUserUseCase, GetUserPasswordsUseCase getUserPasswordsUseCase, UpdateUserUseCase updateUserUseCase, IPasswordMapper passwordMapper, IUserMapper userMapper) {
        this.getUserUseCase = getUserUseCase;
        this.getUserPasswordsUseCase = getUserPasswordsUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.passwordMapper = passwordMapper;
        this.userMapper = userMapper;
    }

    public CustomPage<PasswordResponse> getUserPasswords(UUID userId, int pageNumber, int pageSize, String sortAttribute) {
        User _user = getUserUseCase.getUserById(userId);
        return getUserPasswordsUseCase.getUserPasswords(_user, pageNumber, pageSize, sortAttribute)
                .map(passwordMapper::toPasswordResponse);
    }

    public UserResponse updateUser(UUID userId, UserRequest userRequest) {
        var _user = userMapper.toUser(userRequest);
        return userMapper.toUserResponse( updateUserUseCase.updateUser(userId, _user) );
    }

}