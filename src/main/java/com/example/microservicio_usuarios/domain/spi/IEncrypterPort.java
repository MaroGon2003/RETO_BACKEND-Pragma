package com.example.microservicio_usuarios.domain.spi;

public interface IEncrypterPort {

    String encryptPassword(String password);

}
