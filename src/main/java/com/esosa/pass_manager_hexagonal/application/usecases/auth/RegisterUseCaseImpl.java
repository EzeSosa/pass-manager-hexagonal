package com.esosa.pass_manager_hexagonal.application.usecases.auth;

import com.esosa.pass_manager_hexagonal.domain.model.User;
import com.esosa.pass_manager_hexagonal.domain.ports.input.auth.EncodePasswordUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.auth.RegisterUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.SaveUserUseCase;

public class RegisterUseCaseImpl implements RegisterUseCase {

    private final SaveUserUseCase saveUserUseCase;
    private final EncodePasswordUseCase encodePasswordUseCase;

    public RegisterUseCaseImpl(SaveUserUseCase saveUserUseCase, EncodePasswordUseCase encodePasswordUseCase) {
        this.saveUserUseCase = saveUserUseCase;
        this.encodePasswordUseCase = encodePasswordUseCase;
    }

    @Override
    public void register(User user) {
        String _encodedPassword = encodePasswordUseCase.encodePassword(user.getPassword());
        user.setPassword(_encodedPassword);
        saveUserUseCase.saveUser(user);
    }

}