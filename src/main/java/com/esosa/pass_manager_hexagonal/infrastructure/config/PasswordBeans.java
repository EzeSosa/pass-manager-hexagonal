package com.esosa.pass_manager_hexagonal.infrastructure.config;

import com.esosa.pass_manager_hexagonal.application.services.PasswordService;
import com.esosa.pass_manager_hexagonal.application.usecases.password.*;
import com.esosa.pass_manager_hexagonal.domain.ports.input.password.*;
import com.esosa.pass_manager_hexagonal.domain.ports.output.PasswordPersistencePort;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.PasswordJpaPersistenceAdapter;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.repositories.PasswordRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordBeans {

    private final PasswordRepository passwordRepository;

    public PasswordBeans(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    @Bean
    public PasswordPersistencePort passwordPersistencePort() {
        return new PasswordJpaPersistenceAdapter(passwordRepository);
    }

    @Bean
    public PasswordService passwordService() {
        return new PasswordService(
                savePasswordUseCase(),
                getAllPasswordsUseCase(),
                getPasswordUseCase(),
                updatePasswordUseCase(),
                deletePasswordUseCase()
        );
    }

    @Bean
    public SavePasswordUseCase savePasswordUseCase() {
        return new SavePasswordUseCaseImpl(passwordPersistencePort());
    }

    @Bean
    public GetAllPasswordsUseCase getAllPasswordsUseCase() {
        return new GetAllPasswordsUseCaseImpl(passwordPersistencePort());
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

}