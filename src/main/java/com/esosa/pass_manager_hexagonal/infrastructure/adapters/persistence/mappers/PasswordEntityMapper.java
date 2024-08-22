package com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.mappers;

import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.entities.PasswordEntity;

public class PasswordEntityMapper {

    public static Password toPasswordDomain(PasswordEntity passwordEntity) {
        return Password.builder()
                .id( passwordEntity.getId() )
                .name( passwordEntity.getName() )
                .password( passwordEntity.getPassword() )
                .createdAt( passwordEntity.getCreatedAt() )
                .user( UserEntityMapper.toUserDomain(passwordEntity.getUser()) )
                .build();
    }

    public static PasswordEntity toPasswordEntity(Password password) {
        return new PasswordEntity(
                password.getId(),
                password.getName(),
                password.getPassword(),
                password.getCreatedAt(),
                UserEntityMapper.toUserEntity(password.getUser())
        );
    }

}