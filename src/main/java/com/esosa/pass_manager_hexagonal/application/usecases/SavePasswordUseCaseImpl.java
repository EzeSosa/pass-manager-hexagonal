package com.esosa.pass_manager_hexagonal.application.usecases;

import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.ports.input.SavePasswordUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.PasswordPersistencePort;

import java.security.SecureRandom;
import java.util.Base64;

public class SavePasswordUseCaseImpl implements SavePasswordUseCase {

    private final PasswordPersistencePort passwordPersistencePort;

    public SavePasswordUseCaseImpl(PasswordPersistencePort passwordPersistencePort) {
        this.passwordPersistencePort = passwordPersistencePort;
    }

    @Override
    public Password savePassword(Password password) {
        password.setPassword(generatePassword());
        return passwordPersistencePort.savePassword(password);
    }

    private String generatePassword() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] secretKeyBytes = new byte[16];
        secureRandom.nextBytes(secretKeyBytes);

        return Base64.getEncoder()
                .encodeToString(secretKeyBytes);
    }

}