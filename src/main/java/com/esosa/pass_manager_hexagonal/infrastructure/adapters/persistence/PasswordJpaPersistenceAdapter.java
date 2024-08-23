package com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence;

import com.esosa.pass_manager_hexagonal.domain.model.Password;
import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.output.persistence.PasswordPersistencePort;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.entities.PasswordEntity;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.entities.UserEntity;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.mappers.PasswordEntityMapper;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.mappers.UserEntityMapper;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.repositories.PasswordRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class PasswordJpaPersistenceAdapter implements PasswordPersistencePort {

    private final PasswordRepository passwordRepository;

    public PasswordJpaPersistenceAdapter(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    @Override
    public Password savePassword(Password password) {
        PasswordEntity _passwordEntity = PasswordEntityMapper.toPasswordEntity(password);
        passwordRepository.save(_passwordEntity);
        return PasswordEntityMapper.toPasswordDomain(_passwordEntity);
    }

    @Override
    public List<Password> getAllPasswords() {
        return passwordRepository.findAll().stream()
                .map(PasswordEntityMapper::toPasswordDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Password> getPassword(UUID passwordId) {
        return passwordRepository.findById(passwordId)
                .map(PasswordEntityMapper::toPasswordDomain);
    }

    @Override
    public Password updatePassword(Password password) {
        return savePassword(password);
    }

    @Override
    public void deletePassword(UUID passwordId) {
        PasswordEntity _passwordEntity = passwordRepository.findById(passwordId)
                .orElseThrow();
        _passwordEntity.setDeleted(true);
        passwordRepository.save(_passwordEntity);
    }

    @Override
    public boolean existsPasswordById(UUID passwordId) {
        return passwordRepository.existsById(passwordId);
    }

    @Override
    public List<Password> getUserPasswords(User user) {
        UserEntity _userEntity = UserEntityMapper.toUserEntity(user);
        return passwordRepository.findByUser(_userEntity).stream()
                .map(PasswordEntityMapper::toPasswordDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsPasswordByNameAndUser(String name, User user) {
        UserEntity _userEntity = UserEntityMapper.toUserEntity(user);
        return passwordRepository.existsByNameAndUser(name, _userEntity);
    }

}