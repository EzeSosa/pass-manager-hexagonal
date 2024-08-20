package com.esosa.pass_manager_hexagonal.application.usecases.password;

import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.password.GetUserPasswordsUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.PasswordPersistencePort;

import java.util.List;

public class GetUserPasswordsUseCaseImpl implements GetUserPasswordsUseCase {

    private final PasswordPersistencePort passwordPersistencePort;

    public GetUserPasswordsUseCaseImpl(PasswordPersistencePort passwordPersistencePort) {
        this.passwordPersistencePort = passwordPersistencePort;
    }

    @Override
    public List<Password> getUserPasswords(User user) {
        return passwordPersistencePort.getUserPasswords(user);
    }

}