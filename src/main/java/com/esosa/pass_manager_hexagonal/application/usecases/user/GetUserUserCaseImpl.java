package com.esosa.pass_manager_hexagonal.application.usecases.user;

import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.GetUserUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.UserPersistencePort;

import java.util.NoSuchElementException;
import java.util.UUID;

public class GetUserUserCaseImpl implements GetUserUseCase {

    private final UserPersistencePort userPersistencePort;

    public GetUserUserCaseImpl(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public User getUserById(UUID userId) {
        return userPersistencePort.getUserById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with id " + userId + " does not exist."));
    }

    @Override
    public User getUserByUsername(String username) {
        return userPersistencePort.getUserByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User with username " + username + " does not exist."));
    }

}