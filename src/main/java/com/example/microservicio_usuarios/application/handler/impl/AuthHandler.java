package com.example.microservicio_usuarios.application.handler.impl;

import com.example.microservicio_usuarios.application.dto.request.LoginRequestDto;
import com.example.microservicio_usuarios.application.dto.request.UserRequestDto;
import com.example.microservicio_usuarios.application.dto.response.AuthResponseDto;
import com.example.microservicio_usuarios.application.handler.IAuthHandler;
import com.example.microservicio_usuarios.application.mapper.IUserRequestMapper;
import com.example.microservicio_usuarios.domain.api.IUserServicePort;
import com.example.microservicio_usuarios.infrastructure.configuration.security.jwt.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthHandler implements IAuthHandler {

    private final AuthenticationManager authenticationManager;
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = JwtToken.generateToken(userDetails);
        return new AuthResponseDto(jwt);
    }

    @Override
    public void register(UserRequestDto userRequestDto) {
        userServicePort.registerUser(userRequestMapper.toUserModel(userRequestDto));
    }
}
