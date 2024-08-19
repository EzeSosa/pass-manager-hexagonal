package com.esosa.pass_manager_hexagonal.application.usecases;

import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.ports.input.GetPasswordUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.PasswordPersistencePort;

import java.util.NoSuchElementException;
import java.util.UUID;

public class GetPasswordUseCaseImpl implements GetPasswordUseCase {

    private final PasswordPersistencePort passwordPersistencePort;

    public GetPasswordUseCaseImpl(PasswordPersistencePort passwordPersistencePort) {
        this.passwordPersistencePort = passwordPersistencePort;
    }

    @Override
    public Password getPassword(UUID passwordId) {
        return passwordPersistencePort.getPassword(passwordId)
                .orElseThrow(() -> new NoSuchElementException("Password with id " + passwordId + " does not exist."));
    }

}