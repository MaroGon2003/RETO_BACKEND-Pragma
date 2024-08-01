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
    @Pattern(regexp = "\\+?\\d+", message = "El celular debe contener un máximo de 13 caracteres y puede contener el símbolo +")
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
    private Long rolId;

}
