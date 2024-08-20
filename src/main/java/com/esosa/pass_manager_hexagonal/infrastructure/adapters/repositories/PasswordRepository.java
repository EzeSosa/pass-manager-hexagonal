package com.esosa.pass_manager_hexagonal.infrastructure.adapters.repositories;

import com.esosa.pass_manager_hexagonal.infrastructure.adapters.entities.PasswordEntity;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PasswordRepository extends JpaRepository<PasswordEntity, UUID> {
    List<PasswordEntity> findByUser(UserEntity user);
}