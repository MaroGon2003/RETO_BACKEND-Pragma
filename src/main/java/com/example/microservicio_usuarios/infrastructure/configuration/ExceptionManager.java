package com.example.microservicio_usuarios.infrastructure.configuration;

import com.example.microservicio_usuarios.domain.exception.UserAlreadyExists;
import com.example.microservicio_usuarios.domain.exception.UserNotFoundException;
import com.example.microservicio_usuarios.domain.exception.UserUnderAgeException;
import com.example.microservicio_usuarios.infrastructure.out.jpa.exception.RoleNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ExceptionManager {

    public static final String ERROR = "error";

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<Map<String,String>> userAlreadyExistException(UserAlreadyExists e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

    @ExceptionHandler(UserUnderAgeException.class)
    public ResponseEntity<Map<String,String>> userUnderAgeException(UserUnderAgeException e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Map<String,String>> roleNotFoundException(RoleNotFoundException e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String,String>> userNotFoundException(UserNotFoundException e) {
        return ResponseEntity.badRequest().body(Map.of(ERROR, e.getMessage()));
    }

}
