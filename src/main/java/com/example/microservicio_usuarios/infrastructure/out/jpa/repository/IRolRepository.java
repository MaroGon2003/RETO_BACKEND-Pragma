package com.example.microservicio_usuarios.infrastructure.out.jpa.repository;

import com.example.microservicio_usuarios.infrastructure.out.jpa.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<RolEntity, Long> {
}
