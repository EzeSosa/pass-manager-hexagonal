package com.esosa.pass_manager_hexagonal.infrastructure.controllers.interfaces;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.CreatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.requests.UpdatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.PasswordResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/passwords")
@Tag(
        name = "Passwords",
        description = "Allows registered users to get, register, update and delete a password."
)
public interface IPasswordController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Generates a new password for a registered user")
    PasswordResponse savePassword(@RequestBody CreatePasswordRequest createPasswordRequest);

    @GetMapping("/{passwordId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Fetches a registered password by its id")
    PasswordResponse getPassword(@PathVariable UUID passwordId);

    @PatchMapping("/{passwordId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Updates a registered password")
    PasswordResponse updatePassword(@PathVariable UUID passwordId, @RequestBody UpdatePasswordRequest updatePasswordRequest);

    @DeleteMapping("/{passwordId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Logically deletes an existent password")
    void deletePassword(@PathVariable UUID passwordId);

}