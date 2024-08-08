package com.example.microservicio_usuarios.infrastructure.out.jpa.mapper;

import com.example.microservicio_usuarios.domain.model.RolModel;
import com.example.microservicio_usuarios.infrastructure.out.jpa.entity.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRolEntityMapper {

    RolModel toRolModel(RolEntity rolEntity);

}
