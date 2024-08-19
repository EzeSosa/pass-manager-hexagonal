package com.esosa.pass_manager_hexagonal.infrastructure.controllers;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.password.CreatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.requests.password.UpdatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.services.PasswordService;
import com.esosa.pass_manager_hexagonal.domain.model.Password;
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
    public Password savePassword(@RequestBody CreatePasswordRequest createPasswordRequest) {
        return passwordService.savePassword(createPasswordRequest);
    }

    @GetMapping @ResponseStatus(HttpStatus.OK)
    public List<Password> getAllPasswords() {
        return passwordService.getAllPasswords();
    }

    @GetMapping("/{passwordId}") @ResponseStatus(HttpStatus.OK)
    public Password getPassword(@PathVariable UUID passwordId) {
        return passwordService.getPassword(passwordId);
    }

    @PatchMapping("/{passwordId}") @ResponseStatus(HttpStatus.OK)
    public Password updatePassword(@PathVariable UUID passwordId, @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        return passwordService.updatePassword(passwordId, updatePasswordRequest);
    }

    @DeleteMapping("/{passwordId}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePassword(@PathVariable UUID passwordId) {
        passwordService.deletePassword(passwordId);
    }

}