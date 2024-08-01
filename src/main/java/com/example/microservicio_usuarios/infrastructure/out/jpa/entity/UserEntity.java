package com.example.microservicio_usuarios.infrastructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surName;
    private Integer dni;
    private String phone;
    private LocalDate birdDate;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private RolEntity rolId;

}
