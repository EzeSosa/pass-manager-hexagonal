package com.esosa.pass_manager_hexagonal.infrastructure.beans;

import com.esosa.pass_manager_hexagonal.application.mappers.IUserMapper;
import com.esosa.pass_manager_hexagonal.application.services.AuthService;
import com.esosa.pass_manager_hexagonal.application.usecases.auth.EncodePasswordUseCaseImpl;
import com.esosa.pass_manager_hexagonal.application.usecases.auth.LoginUseCaseImpl;
import com.esosa.pass_manager_hexagonal.application.usecases.auth.RegisterUseCaseImpl;
import com.esosa.pass_manager_hexagonal.domain.ports.input.auth.EncodePasswordUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.auth.LoginUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.auth.RegisterUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.GetUserUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.input.user.SaveUserUseCase;
import com.esosa.pass_manager_hexagonal.domain.ports.output.auth.AuthenticationPort;
import com.esosa.pass_manager_hexagonal.domain.ports.output.auth.PasswordEncodingPort;
import com.esosa.pass_manager_hexagonal.domain.ports.output.auth.TokenManagementPort;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.auth.AuthenticationAdapter;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.auth.PasswordEncodingAdapter;
import com.esosa.pass_manager_hexagonal.infrastructure.adapters.auth.TokenManagementAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthBeans {

    private final SaveUserUseCase saveUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final PasswordEncoder passwordEncoder;
    private final IUserMapper userMapper;

    public AuthBeans(
            @Lazy SaveUserUseCase saveUserUseCase,
            @Lazy PasswordEncoder passwordEncoder,
            @Lazy GetUserUseCase getUserUseCase,
            @Lazy IUserMapper userMapper) {
        this.saveUserUseCase = saveUserUseCase;
        this.passwordEncoder = passwordEncoder;
        this.getUserUseCase = getUserUseCase;
        this.userMapper = userMapper;
    }

    @Bean
    public AuthService authService() {
        return new AuthService( registerUseCase(), loginUseCase(), getUserUseCase, userMapper );
    }

    @Bean
    public RegisterUseCase registerUseCase() {
        return new RegisterUseCaseImpl( saveUserUseCase, encodePasswordUseCase() );
    }

    @Bean
    public LoginUseCase loginUseCase() {
        return new LoginUseCaseImpl(authenticationPort(), tokenManagementPort());
    }

    @Bean
    public EncodePasswordUseCase encodePasswordUseCase() {
        return new EncodePasswordUseCaseImpl(passwordEncodingPort());
    }

    @Bean
    public TokenManagementPort tokenManagementPort() {
        return new TokenManagementAdapter();
    }

    @Bean
    public PasswordEncodingPort passwordEncodingPort() {
        return new PasswordEncodingAdapter(passwordEncoder);
    }

    @Bean
    public AuthenticationPort authenticationPort() {
        return new AuthenticationAdapter();
    }

}