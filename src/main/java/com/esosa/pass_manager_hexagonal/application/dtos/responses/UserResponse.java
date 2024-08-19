package com.esosa.pass_manager_hexagonal.application.dtos.responses;

import java.util.UUID;

public record UserResponse(
        UUID userId,
        String username
) {}