package com.esosa.pass_manager_hexagonal.infrastructure.controllers.implementations;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.CreatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.requests.UpdatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.PasswordResponse;
import com.esosa.pass_manager_hexagonal.application.services.PasswordService;
import com.esosa.pass_manager_hexagonal.infrastructure.controllers.interfaces.IPasswordController;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class PasswordController implements IPasswordController {

    private final PasswordService passwordService;

    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    public PasswordResponse savePassword(CreatePasswordRequest createPasswordRequest) {
        return passwordService.savePassword(createPasswordRequest);
    }

    public PasswordResponse getPassword(UUID passwordId) {
        return passwordService.getPassword(passwordId);
    }

    public PasswordResponse updatePassword(UUID passwordId, UpdatePasswordRequest updatePasswordRequest) {
        return passwordService.updatePassword(passwordId, updatePasswordRequest);
    }

    public void deletePassword(UUID passwordId) {
        passwordService.deletePassword(passwordId);
    }

}