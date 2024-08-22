package com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.mappers;

import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.entities.UserEntity;

public class UserEntityMapper {

    public static User toUserDomain(UserEntity userEntity) {
        return User.builder()
                .id( userEntity.getId() )
                .username( userEntity.getUsername() )
                .password( userEntity.getPassword() )
                .build();
    }

    public static UserEntity toUserEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getPassword()
        );
    }

}