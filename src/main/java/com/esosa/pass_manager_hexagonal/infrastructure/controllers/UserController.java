package com.esosa.pass_manager_hexagonal.infrastructure.controllers;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.CreateUserRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.PasswordResponse;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.UserResponse;
import com.esosa.pass_manager_hexagonal.application.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public UserResponse saveUser(@RequestBody CreateUserRequest createUserRequest) {
        return userService.saveUser(createUserRequest);
    }

    @GetMapping("/{userId}/passwords") @ResponseStatus(HttpStatus.OK)
    public List<PasswordResponse> getUserPasswords(@PathVariable UUID userId) {
        return userService.getUserPasswords(userId);
    }

}