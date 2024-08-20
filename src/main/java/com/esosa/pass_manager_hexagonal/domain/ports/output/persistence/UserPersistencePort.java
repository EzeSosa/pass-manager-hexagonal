package com.esosa.pass_manager_hexagonal.domain.ports.output.persistence;

import com.esosa.pass_manager_hexagonal.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserPersistencePort {

    User saveUser(User user);

    Optional<User> getUserById(UUID userId);

    Optional<User> getUserByUsername(String username);

    boolean existsUserById(UUID userId);

    boolean existsUserByUsername(String username);

}