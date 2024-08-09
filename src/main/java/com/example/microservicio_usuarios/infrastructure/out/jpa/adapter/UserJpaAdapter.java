package com.example.microservicio_usuarios.infrastructure.out.jpa.adapter;

import com.example.microservicio_usuarios.domain.model.RolModel;
import com.example.microservicio_usuarios.domain.model.UserModel;
import com.example.microservicio_usuarios.domain.spi.IUserPersistencePort;
import com.example.microservicio_usuarios.infrastructure.out.jpa.entity.UserEntity;
import com.example.microservicio_usuarios.infrastructure.out.jpa.mapper.IRolEntityMapper;
import com.example.microservicio_usuarios.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.example.microservicio_usuarios.infrastructure.out.jpa.repository.IRolRepository;
import com.example.microservicio_usuarios.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import static com.example.microservicio_usuarios.infrastructure.out.jpa.actions.RoleAuthentication.getRoleWithAuthentication;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRolRepository rolRepository;
    private final IRolEntityMapper rolEntityMapper;

    @Override
    public UserModel saveUser(UserModel user) {
        UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(user));

        userEntity.setRolId(getRoleWithAuthentication(rolRepository));
        System.out.println("su rol es:  ->" + userEntity.getRolId().getName());

        return userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public boolean existsUserById(long id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean exisUserByDni(int dni) {
        return userRepository.existsByDni(dni);
    }

}
