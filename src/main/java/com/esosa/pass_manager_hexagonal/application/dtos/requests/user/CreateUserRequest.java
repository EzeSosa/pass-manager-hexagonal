package com.esosa.pass_manager_hexagonal.application.dtos.requests.user;

public record CreateUserRequest(
        String username,
        String password
) {}