package com.esosa.pass_manager_hexagonal.application.services;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.UserRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.AuthResponse;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.UserResponse;
import com.esosa.pass_manager_hexagonal.application.mappers.UserMapper;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.auth.LoginUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.auth.RegisterUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.GetUserUseCase;

public class AuthService {

    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private final GetUserUseCase getUserUseCase;

    public AuthService(RegisterUseCase registerUseCase, LoginUseCase loginUseCase, GetUserUseCase getUserUseCase) {
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
        this.getUserUseCase = getUserUseCase;
    }

    public void register(UserRequest userRequest) {
        User _user = UserMapper.toUser(userRequest);
        registerUseCase.register(_user);
    }

    public AuthResponse login(UserRequest userRequest) {
        User _user = getUserUseCase.getUserByUsername(userRequest.username());
        _user.setPassword(userRequest.password());
        String _accessToken = loginUseCase.login(_user);

        UserResponse _userResponse = UserMapper.toUserResponse(_user);
        return new AuthResponse( _userResponse, _accessToken );
    }

}