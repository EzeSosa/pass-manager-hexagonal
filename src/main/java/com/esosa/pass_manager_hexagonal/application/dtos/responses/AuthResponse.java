package com.esosa.pass_manager_hexagonal.application.dtos.responses;

import com.esosa.pass_manager_hexagonal.domain.model.User;

public record AuthResponse(
        User user,
        String accessToken
) {}