package com.esosa.pass_manager_hexagonal.application.usecases.password;

import com.esosa.pass_manager_hexagonal.domain.shared.CustomPage;
import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.password.GetUserPasswordsUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.persistence.PasswordPersistencePort;

public class GetUserPasswordsUseCaseImpl implements GetUserPasswordsUseCase {

    private final PasswordPersistencePort passwordPersistencePort;

    public GetUserPasswordsUseCaseImpl(PasswordPersistencePort passwordPersistencePort) {
        this.passwordPersistencePort = passwordPersistencePort;
    }

    @Override
    public CustomPage<Password> getUserPasswords(User user, int pageNumber, int pageSize, String sortBy) {
        return passwordPersistencePort.getUserPasswords(user, pageNumber, pageSize, sortBy);
    }

}