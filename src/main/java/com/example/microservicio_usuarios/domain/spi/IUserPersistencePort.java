package com.example.microservicio_usuarios.domain.spi;

import com.example.microservicio_usuarios.domain.model.UserModel;

public interface IUserPersistencePort {

    UserModel saveUser(UserModel user);

    UserModel getUserById(long userId);

    boolean existUserByDni(int dni);

}
