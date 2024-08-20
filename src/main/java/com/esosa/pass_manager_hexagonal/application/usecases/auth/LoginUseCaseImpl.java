package com.esosa.pass_manager_hexagonal.application.usecases.auth;

import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.auth.LoginUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.auth.AuthenticationPort;
import com.esosa.pass_manager_hexagonal.domain.ports.output.auth.TokenManagementPort;

public class LoginUseCaseImpl implements LoginUseCase {

    private final AuthenticationPort authenticationPort;
    private final TokenManagementPort tokenManagementPort;

    public LoginUseCaseImpl(AuthenticationPort authenticationPort, TokenManagementPort tokenManagementPort) {
        this.authenticationPort = authenticationPort;
        this.tokenManagementPort = tokenManagementPort;
    }

    @Override
    public String login(User user) {
        authenticationPort.authenticate(user);
        return tokenManagementPort.generateToken(user);
    }

}