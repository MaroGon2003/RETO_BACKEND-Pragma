package com.example.microservicio_usuarios.infrastructure.out.encrypter.adapter;

import com.example.microservicio_usuarios.domain.spi.IEncrypterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class EncrypterAdapter implements IEncrypterPort {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
