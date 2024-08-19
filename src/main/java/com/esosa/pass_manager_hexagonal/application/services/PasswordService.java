package com.esosa.pass_manager_hexagonal.application.services;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.CreatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.requests.UpdatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.PasswordResponse;
import com.esosa.pass_manager_hexagonal.application.mappers.PasswordMapper;
import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.password.*;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.GetUserUseCase;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PasswordService {

    private final SavePasswordUseCase savePasswordUseCase;
    private final GetAllPasswordsUseCase getAllPasswordsUseCase;
    private final GetPasswordUseCase getPasswordUseCase;
    private final UpdatePasswordUseCase updatePasswordUseCase;
    private final DeletePasswordUseCase deletePasswordUseCase;
    private final GetUserUseCase getUserUseCase;

    public PasswordService(SavePasswordUseCase savePasswordUseCase,
                           GetAllPasswordsUseCase getAllPasswordsUseCase,
                           GetPasswordUseCase getPasswordUseCase,
                           UpdatePasswordUseCase updatePasswordUseCase,
                           DeletePasswordUseCase deletePasswordUseCase,
                           GetUserUseCase getUserUseCase) {
        this.savePasswordUseCase = savePasswordUseCase;
        this.getAllPasswordsUseCase = getAllPasswordsUseCase;
        this.getPasswordUseCase = getPasswordUseCase;
        this.updatePasswordUseCase = updatePasswordUseCase;
        this.deletePasswordUseCase = deletePasswordUseCase;
        this.getUserUseCase = getUserUseCase;
    }

    public PasswordResponse savePassword(CreatePasswordRequest createPasswordRequest) {
        User _user = getUserUseCase.getUserById(createPasswordRequest.userId());
        Password _password = PasswordMapper.toPassword(createPasswordRequest, _user);
        Password _savedPassword = savePasswordUseCase.savePassword(_password);
        return PasswordMapper.toPasswordResponse(_savedPassword);
    }

    public List<PasswordResponse> getAllPasswords() {
        return getAllPasswordsUseCase.getAllPasswords().stream()
                .map(PasswordMapper::toPasswordResponse)
                .collect(Collectors.toList());
    }

    public PasswordResponse getPassword(UUID passwordId) {
        Password _password = getPasswordUseCase.getPassword(passwordId);
        return PasswordMapper.toPasswordResponse(_password);
    }

    public PasswordResponse updatePassword(UUID passwordId, UpdatePasswordRequest updatePasswordRequest) {
        Password _password = PasswordMapper.toPassword(updatePasswordRequest);
        Password _updatedPassword = updatePasswordUseCase.updatePassword(passwordId, _password);
        return PasswordMapper.toPasswordResponse(_updatedPassword);
    }

    public void deletePassword(UUID passwordId) {
        deletePasswordUseCase.deletePassword(passwordId);
    }

}