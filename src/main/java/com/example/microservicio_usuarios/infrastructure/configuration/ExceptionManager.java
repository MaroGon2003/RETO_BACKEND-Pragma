package com.example.microservicio_usuarios.infrastructure.configuration;

import com.example.microservicio_usuarios.domain.exception.UserAlreadyExists;
import com.example.microservicio_usuarios.domain.exception.UserUnderAgeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<Map<String,String>> userAlreadyExistException(UserAlreadyExists e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }

    @ExceptionHandler(UserUnderAgeException.class)
    public ResponseEntity<Map<String,String>> userUnderAgeException(UserUnderAgeException e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
    }

}
