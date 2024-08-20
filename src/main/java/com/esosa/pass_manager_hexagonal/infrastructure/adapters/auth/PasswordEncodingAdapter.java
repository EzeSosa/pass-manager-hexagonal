package com.esosa.pass_manager_hexagonal.infrastructure.adapters.auth;

import com.esosa.pass_manager_hexagonal.domain.ports.output.auth.PasswordEncodingPort;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncodingAdapter implements PasswordEncodingPort {

    private final PasswordEncoder passwordEncoder;

    public PasswordEncodingAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

}