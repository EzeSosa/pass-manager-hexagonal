package com.esosa.pass_manager_hexagonal.infrastructure.controllers;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.UserRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.AuthResponse;
import com.esosa.pass_manager_hexagonal.application.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register") @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserRequest userRequest) {
        authService.register(userRequest);
    }

    @PostMapping("/login") @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse login(@RequestBody UserRequest userRequest) {
        return authService.login(userRequest);
    }

}