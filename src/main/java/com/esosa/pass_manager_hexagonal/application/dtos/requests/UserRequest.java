package com.esosa.pass_manager_hexagonal.application.dtos.requests;

public record UserRequest(
        String username,
        String password
) {}