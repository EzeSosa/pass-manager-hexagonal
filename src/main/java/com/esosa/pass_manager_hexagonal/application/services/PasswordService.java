package com.esosa.pass_manager_hexagonal.application.services;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.password.CreatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.requests.password.UpdatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.mappers.PasswordMapper;
import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.password.*;

import java.util.List;
import java.util.UUID;

public class PasswordService {

    private final SavePasswordUseCase savePasswordUseCase;
    private final GetAllPasswordsUseCase getAllPasswordsUseCase;
    private final GetPasswordUseCase getPasswordUseCase;
    private final UpdatePasswordUseCase updatePasswordUseCase;
    private final DeletePasswordUseCase deletePasswordUseCase;
    private final UserService userService;

    public PasswordService(SavePasswordUseCase savePasswordUseCase,
                           GetAllPasswordsUseCase getAllPasswordsUseCase,
                           GetPasswordUseCase getPasswordUseCase,
                           UpdatePasswordUseCase updatePasswordUseCase,
                           DeletePasswordUseCase deletePasswordUseCase, UserService userService) {
        this.savePasswordUseCase = savePasswordUseCase;
        this.getAllPasswordsUseCase = getAllPasswordsUseCase;
        this.getPasswordUseCase = getPasswordUseCase;
        this.updatePasswordUseCase = updatePasswordUseCase;
        this.deletePasswordUseCase = deletePasswordUseCase;
        this.userService = userService;
    }

    public Password savePassword(CreatePasswordRequest createPasswordRequest) {
        User _user = userService.getUserById(createPasswordRequest.userId());
        Password _password = PasswordMapper.toPassword(createPasswordRequest, _user);
        return savePasswordUseCase.savePassword(_password);
    }

    public List<Password> getAllPasswords() {
        return getAllPasswordsUseCase.getAllPasswords();
    }

    public Password getPassword(UUID passwordId) {
        return getPasswordUseCase.getPassword(passwordId);
    }

    public Password updatePassword(UUID passwordId, UpdatePasswordRequest updatePasswordRequest) {
        Password _password = PasswordMapper.toPassword(updatePasswordRequest);
        return updatePasswordUseCase.updatePassword(passwordId, _password);
    }

    public void deletePassword(UUID passwordId) {
        deletePasswordUseCase.deletePassword(passwordId);
    }

}