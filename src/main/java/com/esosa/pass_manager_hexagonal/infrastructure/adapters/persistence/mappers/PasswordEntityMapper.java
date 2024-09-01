package com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.mappers;

import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.entities.PasswordEntity;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.entities.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class PasswordEntityMapper {

    @Autowired
    private ObjectMapper objectMapper;

    public Password toPasswordDomain(PasswordEntity passwordEntity) {
        Password _password = objectMapper.convertValue( passwordEntity, Password.class );
        _password.setUser(objectMapper.convertValue( passwordEntity.getUser(), User.class ));
        return _password;
    }

    public PasswordEntity toPasswordEntity(Password password) {
        PasswordEntity _passwordEntity = objectMapper.convertValue( password, PasswordEntity.class );
        _passwordEntity.setUser( objectMapper.convertValue (password.getUser(), UserEntity.class) );
        return _passwordEntity;
    }

}