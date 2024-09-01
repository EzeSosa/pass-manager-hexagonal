package com.esosa.pass_manager_hexagonal.application.dtos.responses;

import java.util.UUID;

public record PasswordResponse(
        UUID id,
        String name,
        String password
) {}