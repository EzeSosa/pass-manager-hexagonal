package com.esosa.pass_manager_hexagonal.application.services;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.CreatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.requests.UpdatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.PasswordResponse;
import com.esosa.pass_manager_hexagonal.application.mappers.IPasswordMapper;
import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.password.*;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.GetUserUseCase;

import java.util.UUID;

public class PasswordService {

    private final SavePasswordUseCase savePasswordUseCase;
    private final GetPasswordUseCase getPasswordUseCase;
    private final UpdatePasswordUseCase updatePasswordUseCase;
    private final DeletePasswordUseCase deletePasswordUseCase;
    private final GetUserUseCase getUserUseCase;
    private final IPasswordMapper passwordMapper;

    public PasswordService(SavePasswordUseCase savePasswordUseCase,
                           GetPasswordUseCase getPasswordUseCase,
                           UpdatePasswordUseCase updatePasswordUseCase,
                           DeletePasswordUseCase deletePasswordUseCase,
                           GetUserUseCase getUserUseCase, IPasswordMapper passwordMapper) {
        this.savePasswordUseCase = savePasswordUseCase;
        this.getPasswordUseCase = getPasswordUseCase;
        this.updatePasswordUseCase = updatePasswordUseCase;
        this.deletePasswordUseCase = deletePasswordUseCase;
        this.getUserUseCase = getUserUseCase;
        this.passwordMapper = passwordMapper;
    }

    public PasswordResponse savePassword(CreatePasswordRequest createPasswordRequest) {
        User _user = getUserUseCase.getUserById(createPasswordRequest.userId());
        Password _password = passwordMapper.toPassword(createPasswordRequest, _user);
        _password.setUser(_user);

        Password _savedPassword = savePasswordUseCase.savePassword(_password);
        return passwordMapper.toPasswordResponse(_savedPassword);
    }

    public PasswordResponse getPassword(UUID passwordId) {
        Password _password = getPasswordUseCase.getPassword(passwordId);
        return passwordMapper.toPasswordResponse(_password);
    }

    public PasswordResponse updatePassword(UUID passwordId, UpdatePasswordRequest updatePasswordRequest) {
        Password _password = passwordMapper.toPassword(updatePasswordRequest);
        Password _updatedPassword = updatePasswordUseCase.updatePassword(passwordId, _password);
        return passwordMapper.toPasswordResponse(_updatedPassword);
    }

    public void deletePassword(UUID passwordId) {
        deletePasswordUseCase.deletePassword(passwordId);
    }

}