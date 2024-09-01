package com.esosa.pass_manager_hexagonal.application.mappers;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.CreatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.requests.UpdatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.PasswordResponse;
import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;

public interface IPasswordMapper {

    Password toPassword(CreatePasswordRequest createPasswordRequest, User user);

    Password toPassword(UpdatePasswordRequest updatePasswordRequest);

    PasswordResponse toPasswordResponse(Password password);
}