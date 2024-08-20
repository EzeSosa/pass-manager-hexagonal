package com.esosa.pass_manager_hexagonal.application.services;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.CreateUserRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.PasswordResponse;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.UserResponse;
import com.esosa.pass_manager_hexagonal.application.mappers.PasswordMapper;
import com.esosa.pass_manager_hexagonal.application.mappers.UserMapper;
import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.password.GetUserPasswordsUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.GetUserUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.SaveUserUseCase;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserService {

    private final SaveUserUseCase saveUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final GetUserPasswordsUseCase getUserPasswordsUseCase;

    public UserService(SaveUserUseCase saveUserUseCase, GetUserUseCase getUserUseCase, GetUserPasswordsUseCase getUserPasswordsUseCase) {
        this.saveUserUseCase = saveUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.getUserPasswordsUseCase = getUserPasswordsUseCase;
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

    public List<PasswordResponse> getUserPasswords(UUID userId) {
        User _user = getUserUseCase.getUserById(userId);
        List<Password> _userPasswords = getUserPasswordsUseCase.getUserPasswords(_user);
        return _userPasswords.stream()
                .map(PasswordMapper::toPasswordResponse)
                .collect(Collectors.toList());
    }

}