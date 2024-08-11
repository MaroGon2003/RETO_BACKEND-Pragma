package com.example.microservicio_usuarios.domain.utils;

public class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final Long ROL_OWNER_ID = 2L;
    public static final Long ROL_CUSTOMER_ID = 4L;


    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String USER_UNDER_AGE = "User is under age";


}
