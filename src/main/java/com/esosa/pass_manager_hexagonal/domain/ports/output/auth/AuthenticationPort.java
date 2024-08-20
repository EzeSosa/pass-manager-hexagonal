package com.esosa.pass_manager_hexagonal.domain.ports.output.auth;

import com.esosa.pass_manager_hexagonal.domain.model.User;

public interface AuthenticationPort {
    void authenticate(User user);
}