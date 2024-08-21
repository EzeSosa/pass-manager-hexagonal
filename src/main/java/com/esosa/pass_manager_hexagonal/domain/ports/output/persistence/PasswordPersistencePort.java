package com.esosa.pass_manager_hexagonal.domain.ports.output.persistence;

import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PasswordPersistencePort {

    Password savePassword(Password password);

    List<Password> getAllPasswords();

    Optional<Password> getPassword(UUID passwordId);

    Password updatePassword(Password password);

    void deletePassword(UUID passwordId);

    boolean existsPasswordById(UUID passwordId);

    List<Password> getUserPasswords(User user);

    boolean existsPasswordByNameAndUser(String name, User user);

}