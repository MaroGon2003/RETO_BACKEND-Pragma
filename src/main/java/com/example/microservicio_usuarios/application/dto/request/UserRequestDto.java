package com.example.microservicio_usuarios.application.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String surName;

    @NotNull
    private Integer dni;

    @NotNull
    @NotBlank
    @Size(max = 13)
    @Pattern(regexp = "\\+?\\d+", message = "The phone number should max 13 digits and only numbers and also can start with +")
    private String phone;

    @NotNull
    private LocalDate birdDate;

    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @Min(value = 2, message = "The rolId should be greater than 1")
    @Max(value = 4, message = "The rolId should be less than 5")
    private Long rolId;

}
