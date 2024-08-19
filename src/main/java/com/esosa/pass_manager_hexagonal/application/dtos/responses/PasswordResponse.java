package com.esosa.pass_manager_hexagonal.application.dtos.responses;

import java.util.UUID;

public record PasswordResponse(
        UUID passwordId,
        String name,
        String password
) {}