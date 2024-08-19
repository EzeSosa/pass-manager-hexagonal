package com.esosa.pass_manager_hexagonal.domain.ports.input;

import com.esosa.pass_manager_hexagonal.domain.model.Password;

public interface SavePasswordUseCase {
    Password savePassword(Password password);
}