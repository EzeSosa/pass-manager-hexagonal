package com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.mappers;

import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.entities.UserEntity;

public class UserEntityMapper {

    public static User toUserDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword()
        );
    }

    public static UserEntity toUserEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getPassword()
        );
    }

}