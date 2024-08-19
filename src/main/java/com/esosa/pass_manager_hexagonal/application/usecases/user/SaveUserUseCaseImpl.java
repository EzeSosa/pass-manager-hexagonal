package com.esosa.pass_manager_hexagonal.application.usecases.user;

import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.SaveUserUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.UserPersistencePort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SaveUserUseCaseImpl implements SaveUserUseCase {

    private final UserPersistencePort userPersistencePort;

    public SaveUserUseCaseImpl(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public User saveUser(User user) {
        ifExistsByUsernameThrowException(user.getUsername());
        return userPersistencePort.saveUser(user);
    }

    public void ifExistsByUsernameThrowException(String username) {
        if ( userPersistencePort.existsUserByUsername(username) )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username " + username + " already taken.");
    }

}