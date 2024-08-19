package com.example.microservicio_usuarios.application.mapper;

import com.example.microservicio_usuarios.application.dto.response.UserResponseDto;
import com.example.microservicio_usuarios.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {

    UserResponseDto toUserResponseDto(UserModel userModel);

}
