package com.example.microservicio_usuarios.infrastructure.input.rest;

import com.example.microservicio_usuarios.application.dto.request.UserRequestDto;
import com.example.microservicio_usuarios.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "Add a new usuario", description = "Creates a new user in the system if the user does not already exist.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveObject(@RequestBody @Valid  UserRequestDto userRequestDto) {
        userHandler.saveUser(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
