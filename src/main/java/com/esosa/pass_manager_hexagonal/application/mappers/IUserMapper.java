package com.esosa.pass_manager_hexagonal.application.mappers;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.UserRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.UserResponse;
import com.esosa.pass_manager_hexagonal.domain.model.User;

public interface IUserMapper {

    User toUser(UserRequest userRequest);

    UserResponse toUserResponse(User user);

}