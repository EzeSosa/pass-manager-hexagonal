package com.esosa.pass_manager_hexagonal.domain.ports.output.persistence;

import com.esosa.pass_manager_hexagonal.domain.extras.CustomPage;
import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface PasswordPersistencePort {

    Password savePassword(Password password);

    Optional<Password> getPassword(UUID passwordId);

    Password updatePassword(Password password);

    void deletePassword(UUID passwordId);

    boolean existsPasswordById(UUID passwordId);

    CustomPage<Password> getUserPasswords(User user, int pageNumber, int pageSize, String sortBy);

    boolean existsPasswordByNameAndUser(String name, User user);

}