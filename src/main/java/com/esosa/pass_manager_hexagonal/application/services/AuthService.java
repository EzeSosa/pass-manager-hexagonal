package com.esosa.pass_manager_hexagonal.application.services;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.UserRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.AuthResponse;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.UserResponse;
import com.esosa.pass_manager_hexagonal.application.mappers.IUserMapper;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.auth.LoginUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.auth.RegisterUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.GetUserUseCase;

public class AuthService {

    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private final GetUserUseCase getUserUseCase;
    private final IUserMapper userMapper;

    public AuthService(RegisterUseCase registerUseCase, LoginUseCase loginUseCase, GetUserUseCase getUserUseCase, IUserMapper userMapper) {
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
        this.getUserUseCase = getUserUseCase;
        this.userMapper = userMapper;
    }

    public void register(UserRequest userRequest) {
        User _user = userMapper.toUser(userRequest);
        registerUseCase.register(_user);
    }

    public AuthResponse login(UserRequest userRequest) {
        User _user = getUserUseCase.getUserByUsername(userRequest.username());
        _user.setPassword(userRequest.password());
        String _accessToken = loginUseCase.login(_user);

        UserResponse _userResponse = userMapper.toUserResponse(_user);
        return new AuthResponse( _userResponse, _accessToken );
    }

}