package com.example.microservicio_usuarios.domain.useCase;

import com.example.microservicio_usuarios.domain.api.IUserServicePort;
import com.example.microservicio_usuarios.domain.exception.UserNotFoundException;
import com.example.microservicio_usuarios.domain.exception.UserUnderAgeException;
import com.example.microservicio_usuarios.domain.model.UserModel;
import com.example.microservicio_usuarios.domain.spi.IEncrypterPort;
import com.example.microservicio_usuarios.domain.spi.IUserPersistencePort;
import com.example.microservicio_usuarios.domain.exception.UserAlreadyExists;
import com.example.microservicio_usuarios.domain.utils.DomainConstants;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;


public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IEncrypterPort encryptPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IEncrypterPort encryptPort) {
        this.userPersistencePort = userPersistencePort;
        this.encryptPort = encryptPort;
    }


    @Override
    public void saveUser(UserModel user) {
        if(userPersistencePort.existUserByDni(user.getDni())){
            throw new UserAlreadyExists(DomainConstants.USER_ALREADY_EXISTS);
        }
        LocalDate birthDateLocal = user.getBirdDate();
        if(Period.between(birthDateLocal, LocalDate.now()).getYears() < 18){
            throw new UserUnderAgeException(DomainConstants.USER_UNDER_AGE);
        }

        user.setPassword(encryptPort.encryptPassword(user.getPassword()));

        userPersistencePort.saveUser(user);
    }

    @Override
    public boolean validateOwner(Long userId) {
        UserModel user = userPersistencePort.getUserById(userId);

        if(user == null){
            throw new UserNotFoundException(DomainConstants.USER_NOT_FOUND);
        }
        return Objects.equals(user.getRolId(), DomainConstants.ROL_OWNER_ID);
    }

    @Override
    public void registerUser(UserModel user) {

        if(userPersistencePort.existUserByDni(user.getDni())){
            throw new UserAlreadyExists(DomainConstants.USER_ALREADY_EXISTS);
        }

        user.setRolId(DomainConstants.ROL_CUSTOMER_ID);

        user.setPassword(encryptPort.encryptPassword(user.getPassword()));

        userPersistencePort.registerUser(user);
    }

    @Override
    public UserModel getUserById(Long userId) {

        UserModel user = userPersistencePort.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException(DomainConstants.USER_NOT_FOUND);
        }
        return user;

    }


}
