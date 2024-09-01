package com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence;

import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.output.persistence.UserPersistencePort;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.entities.UserEntity;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.mappers.UserEntityMapper;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.repositories.UserRepository;

import java.util.Optional;
import java.util.UUID;

public class UserJpaPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserJpaPersistenceAdapter(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public User saveUser(User user) {
        UserEntity _userEntity = userEntityMapper.toUserEntity(user);
        userRepository.save(_userEntity);
        return userEntityMapper.toUserDomain(_userEntity);
    }

    @Override
    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(userEntityMapper::toUserDomain);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userEntityMapper::toUserDomain);
    }

    @Override
    public boolean existsUserById(UUID userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

}