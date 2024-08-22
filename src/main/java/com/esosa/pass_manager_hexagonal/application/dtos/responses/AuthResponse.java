package com.esosa.pass_manager_hexagonal.application.dtos.responses;

public record AuthResponse(
        UserResponse user,
        String accessToken
) {}