package com.esosa.pass_manager_hexagonal.domain.ports.output;

import com.esosa.pass_manager_hexagonal.domain.model.Password;

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

}