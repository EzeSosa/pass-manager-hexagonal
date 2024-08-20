package com.esosa.pass_manager_hexagonal.infrastructure.config;

import com.esosa.pass_manager_hexagonal.application.services.UserService;
import com.esosa.pass_manager_hexagonal.application.usecases.user.GetUserUserCaseImpl;
import com.esosa.pass_manager_hexagonal.application.usecases.user.SaveUserUseCaseImpl;
import com.esosa.pass_manager_hexagonal.domain.ports.input.password.GetUserPasswordsUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.GetUserUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.SaveUserUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.persistence.UserPersistencePort;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.UserJpaPersistenceAdapter;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.persistence.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class UserBeans {

    private final UserRepository userRepository;
    private final GetUserPasswordsUseCase getUserPasswordsUseCase;

    public UserBeans(UserRepository userRepository, @Lazy GetUserPasswordsUseCase getUserPasswordsUseCase) {
        this.userRepository = userRepository;
        this.getUserPasswordsUseCase = getUserPasswordsUseCase;
    }

    @Bean
    public UserPersistencePort userPersistencePort() {
        return new UserJpaPersistenceAdapter(userRepository);
    }

    @Bean
    public UserService userService() {
        return new UserService( getUserUseCase(), getUserPasswordsUseCase);
    }

    @Bean
    public SaveUserUseCase saveUserUseCase() {
        return new SaveUserUseCaseImpl(userPersistencePort());
    }

    @Bean
    public GetUserUseCase getUserUseCase() {
        return new GetUserUserCaseImpl(userPersistencePort());
    }

}