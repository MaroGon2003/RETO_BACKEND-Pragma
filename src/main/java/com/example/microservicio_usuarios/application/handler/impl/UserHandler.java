package com.example.microservicio_usuarios.application.handler.impl;

import com.example.microservicio_usuarios.application.dto.request.UserRequestDto;
import com.example.microservicio_usuarios.application.handler.IUserHandler;
import com.example.microservicio_usuarios.application.mapper.IUserRequestMapper;
import com.example.microservicio_usuarios.domain.api.IUserServicePort;
import com.example.microservicio_usuarios.domain.model.UserModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserRequestMapper userRequestMapper;
    private final IUserServicePort userServicePort;

    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        UserModel user = userRequestMapper.toUserModel(userRequestDto);
        userServicePort.saveUser(user);

    }
}
