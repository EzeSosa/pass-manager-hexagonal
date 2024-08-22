package com.esosa.pass_manager_hexagonal.application.mappers;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.UserRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.UserResponse;
import com.esosa.pass_manager_hexagonal.domain.model.User;

public class UserMapper {

    public static User toUser(UserRequest userRequest) {
        return User.builder()
                .username( userRequest.username() )
                .password( userRequest.password() )
                .build();
    }

    public static UserResponse toUserResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername());
    }

}