package com.esosa.pass_manager_hexagonal.infrastructure.controllers;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.CreatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.requests.UpdatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.PasswordResponse;
import com.esosa.pass_manager_hexagonal.application.services.PasswordService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/passwords")
@RestController
public class PasswordController {

    private final PasswordService passwordService;

    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public PasswordResponse savePassword(@RequestBody CreatePasswordRequest createPasswordRequest) {
        return passwordService.savePassword(createPasswordRequest);
    }

    @GetMapping @ResponseStatus(HttpStatus.OK)
    public List<PasswordResponse> getAllPasswords() {
        return passwordService.getAllPasswords();
    }

    @GetMapping("/{passwordId}") @ResponseStatus(HttpStatus.OK)
    public PasswordResponse getPassword(@PathVariable UUID passwordId) {
        return passwordService.getPassword(passwordId);
    }

    @PatchMapping("/{passwordId}") @ResponseStatus(HttpStatus.OK)
    public PasswordResponse updatePassword(@PathVariable UUID passwordId, @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        return passwordService.updatePassword(passwordId, updatePasswordRequest);
    }

    @DeleteMapping("/{passwordId}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePassword(@PathVariable UUID passwordId) {
        passwordService.deletePassword(passwordId);
    }

}