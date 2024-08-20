package com.esosa.pass_manager_hexagonal.infrastructure.adapters.auth;

import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.output.auth.AuthenticationPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthenticationAdapter implements AuthenticationPort {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void authenticate(User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( user.getUsername(), user.getPassword() )
        );
    }

}