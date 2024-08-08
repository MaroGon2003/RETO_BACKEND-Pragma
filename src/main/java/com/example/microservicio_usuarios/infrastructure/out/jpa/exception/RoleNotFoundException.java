package com.example.microservicio_usuarios.infrastructure.out.jpa.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}
