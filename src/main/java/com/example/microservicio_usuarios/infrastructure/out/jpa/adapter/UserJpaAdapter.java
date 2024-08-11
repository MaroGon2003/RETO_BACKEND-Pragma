package com.example.microservicio_usuarios.infrastructure.out.jpa.adapter;

import com.example.microservicio_usuarios.domain.model.UserModel;
import com.example.microservicio_usuarios.domain.spi.IUserPersistencePort;
import com.example.microservicio_usuarios.infrastructure.out.jpa.entity.UserEntity;
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

    @Override
    public UserModel saveUser(UserModel user) {
        UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(user));

        userEntity.setRolId(getRoleWithAuthentication(rolRepository));

        return userEntityMapper.toUserModel(userEntity);
    }

    @Override
    public UserModel getUserById(long userId) {
        return userEntityMapper.toUserModel(userRepository.findById(userId).orElse(null));
    }

    @Override
    public boolean existUserByDni(int dni) {
        return userRepository.existsByDni(dni);
    }

    @Override
    public void registerUser(UserModel user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }

}
