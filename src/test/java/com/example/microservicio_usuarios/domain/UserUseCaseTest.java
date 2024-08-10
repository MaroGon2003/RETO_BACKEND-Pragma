package com.example.microservicio_usuarios.domain;

import com.example.microservicio_usuarios.domain.exception.UserNotFoundException;
import com.example.microservicio_usuarios.domain.exception.UserUnderAgeException;
import com.example.microservicio_usuarios.domain.factory.UserTestDataFactory;
import com.example.microservicio_usuarios.domain.model.UserModel;
import com.example.microservicio_usuarios.domain.spi.IEncrypterPort;
import com.example.microservicio_usuarios.domain.spi.IUserPersistencePort;
import com.example.microservicio_usuarios.domain.useCase.UserUseCase;
import com.example.microservicio_usuarios.domain.exception.UserAlreadyExists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IEncrypterPort encrypterPort;

    @InjectMocks
    private UserUseCase userUseCase;


    @Test
    void shouldSaveUser() {

        UserModel userModel = UserTestDataFactory.getUserAdminWithSetters();

        when(userPersistencePort.existUserByDni(anyInt())).thenReturn(false);
        when(userPersistencePort.saveUser(any(UserModel.class))).thenReturn(userModel);
        when(encrypterPort.encryptPassword(any(String.class))).thenReturn("string");

        assertDoesNotThrow(() -> userUseCase.saveUser(userModel));
    }


    @Test
    void shouldThrowUserAlreadyExists() {

        UserModel userModel = UserTestDataFactory.getUserOwnerUnderAge();

        when(userPersistencePort.existUserByDni(anyInt())).thenReturn(true);

        assertThrows(UserAlreadyExists.class, () -> userUseCase.saveUser(userModel));

    }

    @Test
    void shouldThrowUserUnderAgeException() {

        UserModel userModel = UserTestDataFactory.getUserOwnerUnderAge();

        when(userPersistencePort.existUserByDni(anyInt())).thenReturn(false);

        assertThrows(UserUnderAgeException.class, () -> userUseCase.saveUser(userModel));

    }

    @Test
    void shoudlValidateOwner() {
        UserModel userModel = UserTestDataFactory.getUserOwnerWithSetters();

        when(userPersistencePort.getUserById(anyLong())).thenReturn(userModel);

        assertDoesNotThrow(() -> userUseCase.validateOwner(1L));
    }

    @Test
    void shouldThrowUserNotFoundException() {
        when(userPersistencePort.getUserById(anyLong())).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> userUseCase.validateOwner(1L));
    }
}
