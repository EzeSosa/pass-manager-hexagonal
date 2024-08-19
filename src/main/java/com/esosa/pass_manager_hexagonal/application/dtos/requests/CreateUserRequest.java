package com.esosa.pass_manager_hexagonal.application.dtos.requests;

public record CreateUserRequest(
        String username,
        String password
) {}