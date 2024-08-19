package com.esosa.pass_manager_hexagonal.application.usecases;

import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.ports.input.GetAllPasswordsUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.PasswordPersistencePort;

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