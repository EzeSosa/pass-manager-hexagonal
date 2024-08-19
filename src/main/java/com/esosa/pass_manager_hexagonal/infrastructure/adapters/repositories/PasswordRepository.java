package com.esosa.pass_manager_hexagonal.infrastructure.adapters.repositories;

import com.esosa.pass_manager_hexagonal.infrastructure.adapters.entities.PasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PasswordRepository extends JpaRepository<PasswordEntity, UUID> {}