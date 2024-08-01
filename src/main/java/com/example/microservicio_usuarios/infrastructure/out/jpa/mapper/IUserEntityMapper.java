package com.example.microservicio_usuarios.infrastructure.out.jpa.mapper;

import com.example.microservicio_usuarios.domain.model.UserModel;
import com.example.microservicio_usuarios.infrastructure.out.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {

    @Mapping(target = "rolId.id", source = "rolId")
    UserEntity toEntity(UserModel userModel);

    @Mapping(target = "rolId", source = "rolId.id")
    UserModel toUserModel(UserEntity userEntity);

}
