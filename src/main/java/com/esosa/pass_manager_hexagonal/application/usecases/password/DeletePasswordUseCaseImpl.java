package com.esosa.pass_manager_hexagonal.application.usecases.password;

import com.esosa.pass_manager_hexagonal.domain.ports.input.password.DeletePasswordUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.persistence.PasswordPersistencePort;

import java.util.NoSuchElementException;
import java.util.UUID;

public class DeletePasswordUseCaseImpl implements DeletePasswordUseCase {

    private final PasswordPersistencePort passwordPersistencePort;

    public DeletePasswordUseCaseImpl(PasswordPersistencePort passwordPersistencePort) {
        this.passwordPersistencePort = passwordPersistencePort;
    }

    @Override
    public void deletePassword(UUID passwordId) {
        ifPasswordDoesNotExistThrowException(passwordId);
        passwordPersistencePort.deletePassword(passwordId);
    }

    private void ifPasswordDoesNotExistThrowException(UUID passwordId) {
        if ( !passwordPersistencePort.existsPasswordById(passwordId) ) {
            throw new NoSuchElementException("Password with id " + passwordId + " does not exist.");
        }
    }

}