package com.esosa.pass_manager_hexagonal.infrastructure.security.services;

import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.GetUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private GetUserUseCase getUserUseCase;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User _user = getUserUseCase.getUserByUsername(username);
        return buildUserDetails(_user);
    }

    private UserDetails buildUserDetails(User user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }

}