package com.example.microservicio_usuarios.infrastructure.input.rest;

import com.example.microservicio_usuarios.application.dto.request.LoginRequestDto;
import com.example.microservicio_usuarios.application.dto.request.UserRequestDto;
import com.example.microservicio_usuarios.application.dto.response.AuthResponseDto;
import com.example.microservicio_usuarios.application.handler.impl.AuthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthHandler authHandler;

     @PostMapping("/login")
     public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto requestDto) {
         return ResponseEntity.ok(authHandler.login(requestDto));
     }

     @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRequestDto requestDto) {
         authHandler.register(requestDto);
         return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
