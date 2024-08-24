package com.esosa.pass_manager_hexagonal.infrastructure.controllers;

import com.esosa.pass_manager_hexagonal.application.dtos.responses.PasswordResponse;
import com.esosa.pass_manager_hexagonal.application.services.UserService;
import com.esosa.pass_manager_hexagonal.domain.extras.CustomPage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/passwords") @ResponseStatus(HttpStatus.OK)
    public CustomPage<PasswordResponse> getUserPasswords(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "createdAt") String sortBy
    ) {
        return userService.getUserPasswords(userId, pageNumber, pageSize, sortBy);
    }

}