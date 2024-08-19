package com.esosa.pass_manager_hexagonal.application.dtos.requests;

import java.util.UUID;

public record CreatePasswordRequest(
        String name,
        UUID userId
) {}