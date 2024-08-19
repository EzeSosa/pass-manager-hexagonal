package com.esosa.pass_manager_hexagonal.infrastructure.adapters.mappers;

import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.entities.UserEntity;

public class UserEntityMapper {

    public static User toUserDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                PasswordEntityMapper.toPasswordDomainList(userEntity.getPasswords())
        );
    }

    public static UserEntity toUserEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                PasswordEntityMapper.toPasswordEntityList(user.getPasswords())
        );
    }

}