package com.esosa.pass_manager_hexagonal.application.usecases.user;

import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.auth.EncodePasswordUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.GetUserUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.UpdateUserUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.persistence.UserPersistencePort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final GetUserUseCase getUserUseCase;
    private final UserPersistencePort userPersistencePort;
    private final EncodePasswordUseCase encodePasswordUseCase;

    public UpdateUserUseCaseImpl(GetUserUseCase getUserUseCase, UserPersistencePort userPersistencePort, EncodePasswordUseCase encodePasswordUseCase) {
        this.getUserUseCase = getUserUseCase;
        this.userPersistencePort = userPersistencePort;
        this.encodePasswordUseCase = encodePasswordUseCase;
    }

    @Override
    public User updateUser(UUID userId, User user) {
        var _user = getUserUseCase.getUserById(userId);
        ifExistsByUsernameThrowException(_user, user.getUsername());

        _user.setUsername(user.getUsername());
        _user.setPassword( encodePasswordUseCase.encodePassword(user.getPassword()) );

        return userPersistencePort.saveUser(_user);
    }

    private void ifExistsByUsernameThrowException(User user, String username) {
        if (!user.getUsername().equalsIgnoreCase(username)) {
            if (userPersistencePort.existsUserByUsername(username)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Userame "+username+" already taken.");
            }
        }
    }

}