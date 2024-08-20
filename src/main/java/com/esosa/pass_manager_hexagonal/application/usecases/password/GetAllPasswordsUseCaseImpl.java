package com.esosa.pass_manager_hexagonal.application.usecases.password;

import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.ports.input.password.GetAllPasswordsUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.persistence.PasswordPersistencePort;

import java.util.List;

public class GetAllPasswordsUseCaseImpl implements GetAllPasswordsUseCase {

    private final PasswordPersistencePort passwordPersistencePort;

    public GetAllPasswordsUseCaseImpl(PasswordPersistencePort passwordPersistencePort) {
        this.passwordPersistencePort = passwordPersistencePort;
    }

    @Override
    public List<Password> getAllPasswords() {
        return passwordPersistencePort.getAllPasswords();
    }

}