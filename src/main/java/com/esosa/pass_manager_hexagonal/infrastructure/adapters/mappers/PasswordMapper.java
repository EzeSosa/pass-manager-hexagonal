package com.esosa.pass_manager_hexagonal.infrastructure.adapters.mappers;

import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.entities.PasswordEntity;

public class PasswordMapper {

    public static Password toPasswordDomain(PasswordEntity passwordEntity) {
        return new Password(passwordEntity.getId(), passwordEntity.getName(), passwordEntity.getPassword(), passwordEntity.getCreatedAt());
    }

    public static PasswordEntity toPasswordEntity(Password password) {
        return new PasswordEntity(password.getId(), password.getName(), password.getPassword(), password.getCreatedAt());
    }

}