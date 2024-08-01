package com.example.microservicio_usuarios.application.handler;

import com.example.microservicio_usuarios.application.dto.request.UserRequestDto;

public interface IUserHandler {

    void saveUser(UserRequestDto userRequestDto);

}
