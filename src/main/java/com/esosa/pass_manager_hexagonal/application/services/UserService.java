package com.esosa.pass_manager_hexagonal.application.services;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.CreateUserRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.UserResponse;
import com.esosa.pass_manager_hexagonal.application.mappers.UserMapper;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.GetUserUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.SaveUserUseCase;

import java.util.UUID;

public class UserService {

    private final SaveUserUseCase saveUserUseCase;
    private final GetUserUseCase getUserUseCase;

    public UserService(SaveUserUseCase saveUserUseCase, GetUserUseCase getUserUseCase) {
        this.saveUserUseCase = saveUserUseCase;
        this.getUserUseCase = getUserUseCase;
    }

    public UserResponse saveUser(CreateUserRequest createUserRequest) {
        User _user = UserMapper.toUser(createUserRequest);
        User _savedUser = saveUserUseCase.saveUser(_user);
        return UserMapper.toUserResponse(_savedUser);
    }

    public UserResponse getUserById(UUID userId) {
        User _user = getUserUseCase.getUserById(userId);
        return UserMapper.toUserResponse(_user);
    }

    public UserResponse getUserByUsername(String username) {
        User _user = getUserUseCase.getUserByUsername(username);
        return UserMapper.toUserResponse(_user);
    }

}