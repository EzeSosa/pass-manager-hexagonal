package com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.mappers;

import com.esosa.pass_manager_hexagonal.application.dtos.requests.CreatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.requests.UpdatePasswordRequest;
import com.esosa.pass_manager_hexagonal.application.dtos.responses.PasswordResponse;
import com.esosa.pass_manager_hexagonal.application.mappers.IPasswordMapper;
import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class PasswordMapper implements IPasswordMapper {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Password toPassword(CreatePasswordRequest createPasswordRequest, User user) {
        Password _password = objectMapper.convertValue(createPasswordRequest, Password.class);
        _password.setUser(user);
        return _password;
    }

    @Override
    public Password toPassword(UpdatePasswordRequest updatePasswordRequest) {
        return objectMapper.convertValue(updatePasswordRequest, Password.class);
    }

    @Override
    public PasswordResponse toPasswordResponse(Password password) {
        return objectMapper.convertValue(password, PasswordResponse.class);
    }

}