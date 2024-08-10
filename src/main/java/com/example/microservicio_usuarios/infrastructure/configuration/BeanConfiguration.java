package com.example.microservicio_usuarios.infrastructure.configuration;

import com.example.microservicio_usuarios.domain.api.IUserServicePort;
import com.example.microservicio_usuarios.domain.spi.IEncrypterPort;
import com.example.microservicio_usuarios.domain.spi.IUserPersistencePort;
import com.example.microservicio_usuarios.domain.useCase.UserUseCase;
import com.example.microservicio_usuarios.infrastructure.out.encrypter.adapter.EncrypterAdapter;
import com.example.microservicio_usuarios.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.example.microservicio_usuarios.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.example.microservicio_usuarios.infrastructure.out.jpa.repository.IRolRepository;
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
    private final IRolRepository rolRepository;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper, rolRepository);
    }

    @Bean
    public IEncrypterPort encrypterPort() {
        return new EncrypterAdapter(encoder());
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), encrypterPort());
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
