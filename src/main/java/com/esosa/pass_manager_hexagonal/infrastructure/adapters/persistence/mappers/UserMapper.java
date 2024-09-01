package com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.mappers;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.UserRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.UserResponse;
import com.esosa.pass_manager_hexagonal.application.mappers.IUserMapper;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapper implements IUserMapper {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public User toUser(UserRequest userRequest) {
        return objectMapper.convertValue(userRequest, User.class);
    }

    @Override
    public UserResponse toUserResponse(User user) {
        return objectMapper.convertValue(user, UserResponse.class);
    }

}