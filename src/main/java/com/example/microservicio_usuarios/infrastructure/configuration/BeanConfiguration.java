package com.example.microservicio_usuarios.infrastructure.configuration;

import com.example.microservicio_usuarios.domain.api.IUserServicePort;
import com.example.microservicio_usuarios.domain.spi.IUserPersistencePort;
import com.example.microservicio_usuarios.domain.useCase.UserUseCase;
import com.example.microservicio_usuarios.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.example.microservicio_usuarios.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.example.microservicio_usuarios.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
