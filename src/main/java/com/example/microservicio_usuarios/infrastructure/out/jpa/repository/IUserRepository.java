package com.example.microservicio_usuarios.infrastructure.out.jpa.repository;

import com.example.microservicio_usuarios.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByDni(Integer dni);

}
