package com.example.microservicio_usuarios.infrastructure.input.rest;

import com.example.microservicio_usuarios.application.dto.request.LoginRequestDto;
import com.example.microservicio_usuarios.application.dto.request.UserRequestDto;
import com.example.microservicio_usuarios.application.dto.response.AuthResponseDto;
import com.example.microservicio_usuarios.application.handler.impl.AuthHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Login to obtain a jwt token", description = "Login to obtain a jwt token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        return ResponseEntity.ok(authHandler.login(requestDto));
    }


    @Operation(summary = "Register a customer in the system", description = "Register a customer in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "customer created", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRequestDto requestDto) {
        authHandler.register(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
