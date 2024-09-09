package com.esosa.pass_manager_hexagonal.infrastructure.controllers.implementations;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.UserRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.PasswordResponse;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.UserResponse;
import com.esosa.pass_manager_hexagonal.application.services.UserService;
import com.esosa.pass_manager_hexagonal.domain.shared.CustomPage;
import com.esosa.pass_manager_hexagonal.infrastructure.controllers.interfaces.IUserController;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController implements IUserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public CustomPage<PasswordResponse> getUserPasswords(
            UUID userId,
            int pageNumber,
            int pageSize,
            String sortBy
    ) {
        return userService.getUserPasswords(userId, pageNumber, pageSize, sortBy);
    }

    public UserResponse updateUser(
            UUID userId,
            UserRequest userRequest
    ) {
        return userService.updateUser(userId, userRequest);
    }

}