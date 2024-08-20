package com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.mappers;

import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.entities.PasswordEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PasswordEntityMapper {

    public static List<Password> toPasswordDomainList(List<PasswordEntity> passwordEntityList) {
        return passwordEntityList.stream()
                .map(PasswordEntityMapper::toPasswordDomain)
                .collect(Collectors.toList());
    }

    public static List<PasswordEntity> toPasswordEntityList(List<Password> passwords) {
        return passwords.stream()
                .map(PasswordEntityMapper::toPasswordEntity)
                .collect(Collectors.toList());
    }

    public static Password toPasswordDomain(PasswordEntity passwordEntity) {
        return new Password(
                passwordEntity.getId(),
                passwordEntity.getName(),
                passwordEntity.getPassword(),
                passwordEntity.getCreatedAt(),
                UserEntityMapper.toUserDomain(passwordEntity.getUser()));
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