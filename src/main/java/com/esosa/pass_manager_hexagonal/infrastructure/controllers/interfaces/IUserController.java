package com.esosa.pass_manager_hexagonal.infrastructure.controllers.interfaces;

import com.esosa.pass_manager_hexagonal.application.dtos.responses.PasswordResponse;
import com.esosa.pass_manager_hexagonal.domain.shared.CustomPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/users")
@Tag(
        name = "Users",
        description = "Allows registered users to fetch their passwords."
)
public interface IUserController {

    @GetMapping("/{userId}/passwords")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Fetches a registered user passwords")
    CustomPage<PasswordResponse> getUserPasswords(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "createdAt") String sortBy
    );

}