package com.esosa.pass_manager_hexagonal.infrastructure.controllers.implementations;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.UserRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.AuthResponse;
import com.esosa.pass_manager_hexagonal.application.services.AuthService;
import com.esosa.pass_manager_hexagonal.infrastructure.controllers.interfaces.IAuthController;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController implements IAuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public void register(UserRequest userRequest) {
        authService.register(userRequest);
    }

    public AuthResponse login(UserRequest userRequest) {
        return authService.login(userRequest);
    }

}