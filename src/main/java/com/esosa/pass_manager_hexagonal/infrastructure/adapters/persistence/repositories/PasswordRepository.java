package com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.repositories;

import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.entities.PasswordEntity;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PasswordRepository extends JpaRepository<PasswordEntity, UUID> {

    @Query(
            "SELECT p FROM passwords p" +
            " WHERE (p.user = ?1)" +
            " AND (p.deleted = false)"
    )
    List<PasswordEntity> findByUser(UserEntity user);

    boolean existsByNameAndUser(String name, UserEntity user);
}