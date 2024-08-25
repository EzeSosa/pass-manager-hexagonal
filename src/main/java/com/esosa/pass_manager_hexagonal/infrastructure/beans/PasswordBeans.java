package com.esosa.pass_manager_hexagonal.infrastructure.beans;

import com.esosa.pass_manager_hexagonal.application.services.PasswordService;
import com.esosa.pass_manager_hexagonal.application.usecases.password.*;
import com.esosa.pass_manager_hexagonal.domain.ports.input.password.*;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.GetUserUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.persistence.PasswordPersistencePort;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.PasswordJpaPersistenceAdapter;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.repositories.PasswordRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class PasswordBeans {

    private final PasswordRepository passwordRepository;
    private final GetUserUseCase getUserUseCase;

    public PasswordBeans(PasswordRepository passwordRepository, @Lazy GetUserUseCase getUserUseCase) {
        this.passwordRepository = passwordRepository;
        this.getUserUseCase = getUserUseCase;
    }

    @Bean
    public PasswordPersistencePort passwordPersistencePort() {
        return new PasswordJpaPersistenceAdapter(passwordRepository);
    }

    @Bean
    public PasswordService passwordService() {
        return new PasswordService(
                savePasswordUseCase(),
                getPasswordUseCase(),
                updatePasswordUseCase(),
                deletePasswordUseCase(),
                getUserUseCase
        );
    }

    @Bean
    public SavePasswordUseCase savePasswordUseCase() {
        return new SavePasswordUseCaseImpl(passwordPersistencePort());
    }

    @Bean
    public GetPasswordUseCase getPasswordUseCase() {
        return new GetPasswordUseCaseImpl(passwordPersistencePort());
    }

    @Bean
    public UpdatePasswordUseCase updatePasswordUseCase() {
        return new UpdatePasswordUseCaseImpl(passwordPersistencePort(), getPasswordUseCase());
    }

    @Bean
    public DeletePasswordUseCase deletePasswordUseCase() {
        return new DeletePasswordUseCaseImpl(passwordPersistencePort());
    }

    @Bean
    public GetUserPasswordsUseCase getUserPasswordsUseCase() {
        return new GetUserPasswordsUseCaseImpl(passwordPersistencePort());
    }

}