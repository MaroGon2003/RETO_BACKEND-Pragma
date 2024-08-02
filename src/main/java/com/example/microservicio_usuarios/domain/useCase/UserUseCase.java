package com.example.microservicio_usuarios.domain.useCase;

import com.example.microservicio_usuarios.domain.api.IUserServicePort;
import com.example.microservicio_usuarios.domain.exception.ErrorMessages;
import com.example.microservicio_usuarios.domain.exception.UserUnderAgeException;
import com.example.microservicio_usuarios.domain.model.UserModel;
import com.example.microservicio_usuarios.domain.spi.IEncrypterPort;
import com.example.microservicio_usuarios.domain.spi.IUserPersistencePort;
import com.example.microservicio_usuarios.domain.exception.UserAlreadyExists;
import java.time.LocalDate;
import java.time.Period;


public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IEncrypterPort encrypterPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IEncrypterPort encrypterPort) {
        this.userPersistencePort = userPersistencePort;
        this.encrypterPort = encrypterPort;
    }


    @Override
    public void saveUser(UserModel user) {
        if(userPersistencePort.exisUserByDni(user.getDni())){
            throw new UserAlreadyExists(ErrorMessages.USER_ALREADY_EXISTS);
        }
        LocalDate birthDateLocal = user.getBirdDate();
        if(Period.between(birthDateLocal, LocalDate.now()).getYears() < 18){
            throw new UserUnderAgeException(ErrorMessages.USER_UNDER_AGE);
        }

        user.setPassword(encrypterPort.encryptPassword(user.getPassword()));

        userPersistencePort.saveUser(user);
    }



}
