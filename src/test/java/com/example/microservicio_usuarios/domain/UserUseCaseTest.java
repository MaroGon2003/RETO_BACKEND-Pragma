package com.example.microservicio_usuarios.domain;

import com.example.microservicio_usuarios.domain.exception.UserUnderAgeException;
import com.example.microservicio_usuarios.domain.factory.UserTestDataFactory;
import com.example.microservicio_usuarios.domain.model.UserModel;
import com.example.microservicio_usuarios.domain.spi.IUserPersistencePort;
import com.example.microservicio_usuarios.domain.useCase.UserUseCase;
import com.example.microservicio_usuarios.domain.exception.UserAlreadyExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;


    @Test
    void shouldSaveUser() {

        UserModel userModel = UserTestDataFactory.getUserAdminWithSetters();

        when(userPersistencePort.exisrUserByDni(anyInt())).thenReturn(false);
        when(userPersistencePort.saveUser(any(UserModel.class))).thenReturn(userModel);

        assertDoesNotThrow(() -> userUseCase.saveUser(userModel));
    }


    @Test
    void shouldThrowUserAlreadyExists() {

        UserModel userModel = UserTestDataFactory.getUserOwnerUnderAge();

        when(userPersistencePort.exisrUserByDni(anyInt())).thenReturn(true);

        assertThrows(UserAlreadyExists.class, () -> userUseCase.saveUser(userModel));

    }

    @Test
    void shouldThrowUserUnderAgeException() {

        UserModel userModel = UserTestDataFactory.getUserOwnerUnderAge();

        when(userPersistencePort.exisrUserByDni(anyInt())).thenReturn(false);

        assertThrows(UserUnderAgeException.class, () -> userUseCase.saveUser(userModel));

    }
}
