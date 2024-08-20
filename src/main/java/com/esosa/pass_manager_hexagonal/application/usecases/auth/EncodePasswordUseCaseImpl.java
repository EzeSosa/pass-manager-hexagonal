package com.esosa.pass_manager_hexagonal.application.usecases.auth;

import com.esosa.pass_manager_hexagonal.domain.ports.input.auth.EncodePasswordUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.auth.PasswordEncodingPort;

public class EncodePasswordUseCaseImpl implements EncodePasswordUseCase {

    private final PasswordEncodingPort passwordEncodingPort;

    public EncodePasswordUseCaseImpl(PasswordEncodingPort passwordEncodingPort) {
        this.passwordEncodingPort = passwordEncodingPort;
    }

    @Override
    public String encodePassword(String rawPassword) {
        return passwordEncodingPort.encodePassword(rawPassword);
    }

}