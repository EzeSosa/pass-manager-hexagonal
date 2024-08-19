package com.esosa.pass_manager_hexagonal.infrastructure.adapters.repositories;

import com.esosa.pass_manager_hexagonal.infrastructure.adapters.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}