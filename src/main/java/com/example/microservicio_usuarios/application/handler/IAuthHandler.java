package com.example.microservicio_usuarios.application.handler;

import com.example.microservicio_usuarios.application.dto.request.LoginRequestDto;
import com.example.microservicio_usuarios.application.dto.request.UserRequestDto;
import com.example.microservicio_usuarios.application.dto.response.AuthResponseDto;

public interface IAuthHandler {
    AuthResponseDto login(LoginRequestDto loginRequestDto);
    void register(UserRequestDto userRequestDto);
}
