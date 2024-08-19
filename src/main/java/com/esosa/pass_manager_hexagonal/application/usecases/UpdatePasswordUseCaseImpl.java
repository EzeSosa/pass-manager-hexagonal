package com.esosa.pass_manager_hexagonal.application.usecases;

import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.ports.input.GetPasswordUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.UpdatePasswordUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.PasswordPersistencePort;

import java.util.UUID;

public class UpdatePasswordUseCaseImpl implements UpdatePasswordUseCase {

    private final PasswordPersistencePort passwordPersistencePort;
    private final GetPasswordUseCase getPasswordUseCase;

    public UpdatePasswordUseCaseImpl(PasswordPersistencePort passwordPersistencePort, GetPasswordUseCase getPasswordUseCase) {
        this.passwordPersistencePort = passwordPersistencePort;
        this.getPasswordUseCase = getPasswordUseCase;
    }

    @Override
    public Password updatePassword(UUID passwordId, Password password) {
        Password _password = getPasswordUseCase.getPassword(passwordId);
        _password.setName(password.getName());
        return passwordPersistencePort.savePassword(_password);
    }

}