package com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.mappers;

import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.entities.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserEntityMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public User toUserDomain(UserEntity userEntity) {
        return objectMapper.convertValue(userEntity, User.class);
    }

    public UserEntity toUserEntity(User user) {
        return objectMapper.convertValue(user, UserEntity.class);
    }

}