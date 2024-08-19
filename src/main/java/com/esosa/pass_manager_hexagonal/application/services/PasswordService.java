package com.esosa.pass_manager_hexagonal.application.services;

import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.ports.input.password.*;

import java.util.List;
import java.util.UUID;

public class PasswordService implements SavePasswordUseCase, GetAllPasswordsUseCase, GetPasswordUseCase,
        UpdatePasswordUseCase, DeletePasswordUseCase {

    private final SavePasswordUseCase savePasswordUseCase;
    private final GetAllPasswordsUseCase getAllPasswordsUseCase;
    private final GetPasswordUseCase getPasswordUseCase;
    private final UpdatePasswordUseCase updatePasswordUseCase;
    private final DeletePasswordUseCase deletePasswordUseCase;

    public PasswordService(SavePasswordUseCase savePasswordUseCase,
                           GetAllPasswordsUseCase getAllPasswordsUseCase,
                           GetPasswordUseCase getPasswordUseCase,
                           UpdatePasswordUseCase updatePasswordUseCase,
                           DeletePasswordUseCase deletePasswordUseCase) {
        this.savePasswordUseCase = savePasswordUseCase;
        this.getAllPasswordsUseCase = getAllPasswordsUseCase;
        this.getPasswordUseCase = getPasswordUseCase;
        this.updatePasswordUseCase = updatePasswordUseCase;
        this.deletePasswordUseCase = deletePasswordUseCase;
    }

    @Override
    public Password savePassword(Password password) {
        return savePasswordUseCase.savePassword(password);
    }

    @Override
    public List<Password> getAllPasswords() {
        return getAllPasswordsUseCase.getAllPasswords();
    }

    @Override
    public Password getPassword(UUID passwordId) {
        return getPasswordUseCase.getPassword(passwordId);
    }

    @Override
    public Password updatePassword(UUID passwordId, Password password) {
        return updatePasswordUseCase.updatePassword(passwordId, password);
    }

    @Override
    public void deletePassword(UUID passwordId) {
        deletePasswordUseCase.deletePassword(passwordId);
    }

}