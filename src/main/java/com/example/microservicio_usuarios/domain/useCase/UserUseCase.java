package com.example.microservicio_usuarios.domain.useCase;

import com.example.microservicio_usuarios.domain.api.IUserServicePort;
import com.example.microservicio_usuarios.domain.exception.UserUnderAgeException;
import com.example.microservicio_usuarios.domain.model.UserModel;
import com.example.microservicio_usuarios.domain.spi.IUserPersistencePort;
import com.example.microservicio_usuarios.domain.exception.UserAlreadyExists;
import java.time.LocalDate;
import java.time.Period;


public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;


    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(UserModel user) {
        if(userPersistencePort.exisrUserByDni(user.getDni())){
            throw new UserAlreadyExists("User already exists");
        }
        LocalDate birthDateLocal = user.getBirdDate();
        if(Period.between(birthDateLocal, LocalDate.now()).getYears() < 18){
            throw new UserUnderAgeException("User is under 18 years old");
        }

        userPersistencePort.saveUser(user);
    }



}
