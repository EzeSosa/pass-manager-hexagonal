package com.esosa.pass_manager_hexagonal.application.mappers;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.CreatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.requests.UpdatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.PasswordResponse;
import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;

public class PasswordMapper {

    public static Password toPassword(CreatePasswordRequest createPasswordRequest, User user) {
        return new Password(createPasswordRequest.name(), user);
    }

    public static Password toPassword(UpdatePasswordRequest updatePasswordRequest) {
        return new Password(updatePasswordRequest.name());
    }

    public static PasswordResponse toPasswordResponse(Password password) {
        return new PasswordResponse(password.getId(), password.getName(), password.getPassword());
    }

}