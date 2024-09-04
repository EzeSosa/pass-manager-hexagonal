package com.esosa.pass_manager_hexagonal.application.dtos.responses;

import com.esosa.pass_manager_hexagonal.domain.model.Role;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        Role role
) {}