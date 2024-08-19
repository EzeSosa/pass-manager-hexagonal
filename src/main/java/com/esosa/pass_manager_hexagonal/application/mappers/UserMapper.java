package com.esosa.pass_manager_hexagonal.application.mappers;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.user.CreateUserRequest;
import com.esosa.pass_manager_hexagonal.domain.model.User;

public class UserMapper {

    public static User toUser(CreateUserRequest createUserRequest) {
        return new User(createUserRequest.username(), createUserRequest.password());
    }

}