package com.esosa.pass_manager_hexagonal.domain.ports.output.auth;

import com.esosa.pass_manager_hexagonal.domain.model.User;

public interface TokenManagementPort {

    String generateToken(User user);

    boolean isTokenValid(String token, String username);

    String extractUsernameFromToken(String token);

}