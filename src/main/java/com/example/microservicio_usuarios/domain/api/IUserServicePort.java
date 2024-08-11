package com.example.microservicio_usuarios.domain.api;

import com.example.microservicio_usuarios.domain.model.UserModel;

public interface IUserServicePort {

        void saveUser(UserModel user);

        boolean validateOwner(Long userId);

        void registerUser(UserModel user);

}
