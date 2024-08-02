package com.example.microservicio_usuarios.infrastructure.out.jpa.adapter;

import com.example.microservicio_usuarios.domain.model.UserModel;
import com.example.microservicio_usuarios.domain.spi.IUserPersistencePort;
import com.example.microservicio_usuarios.infrastructure.out.jpa.entity.UserEntity;
import com.example.microservicio_usuarios.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.example.microservicio_usuarios.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public UserModel saveUser(UserModel user) {
        UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(user));
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
