package com.example.microservicio_usuarios.application.handler;

import com.example.microservicio_usuarios.application.dto.request.UserRequestDto;
import com.example.microservicio_usuarios.application.dto.response.UserResponseDto;

public interface IUserHandler {

    void saveUser(UserRequestDto userRequestDto);

    boolean validateOwner(Long userId);

    UserResponseDto getUserById(Long userId);

}
