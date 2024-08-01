package com.example.microservicio_usuarios.domain.exception;

public class UserUnderAgeException extends RuntimeException {
    public UserUnderAgeException(String message) {
        super(message);
    }
}
