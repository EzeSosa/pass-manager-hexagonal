package com.esosa.pass_manager_hexagonal.application.dtos.requests.password;

import java.util.UUID;

public record CreatePasswordRequest(
        String name,
        UUID userId
) {}