package com.esosa.pass_manager_hexagonal.application.usecases.password;

import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.password.SavePasswordUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.persistence.PasswordPersistencePort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.util.Base64;

public class SavePasswordUseCaseImpl implements SavePasswordUseCase {

    private final PasswordPersistencePort passwordPersistencePort;

    public SavePasswordUseCaseImpl(PasswordPersistencePort passwordPersistencePort) {
        this.passwordPersistencePort = passwordPersistencePort;
    }

    @Override
    public Password savePassword(Password password) {
        ifExistsPasswordByNameAndUserThrowException( password.getName(), password.getUser() );
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

    private void ifExistsPasswordByNameAndUserThrowException(String name, User user) {
        if ( passwordPersistencePort.existsPasswordByNameAndUser(name, user) )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password with name " + name + " already exists for that user.");
    }

}