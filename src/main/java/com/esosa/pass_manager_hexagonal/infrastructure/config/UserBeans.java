package com.esosa.pass_manager_hexagonal.infrastructure.config;

import com.esosa.pass_manager_hexagonal.application.services.UserService;
import com.esosa.pass_manager_hexagonal.application.usecases.user.GetUserUserCaseImpl;
import com.esosa.pass_manager_hexagonal.application.usecases.user.SaveUserUseCaseImpl;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.GetUserUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.SaveUserUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.UserPersistencePort;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.UserJpaPersistenceAdapter;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeans {

    private final UserRepository userRepository;

    public UserBeans(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserPersistencePort userPersistencePort() {
        return new UserJpaPersistenceAdapter(userRepository);
    }

    @Bean
    public UserService userService() {
        return new UserService( saveUserUseCase(), getUserUseCase() );
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