package com.example.microservicio_usuarios.domain.factory;

import com.example.microservicio_usuarios.domain.model.UserModel;

import java.time.LocalDate;

public class UserTestDataFactory {

    public static UserModel getUserAdminWithSetters() {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setName("Admin");
        userModel.setSurName("Admin");
        userModel.setDni(1234567890);
        userModel.setPhone("+571234567890");
        userModel.setEmail("admin@gmail.com");
        userModel.setPassword("admin123");
        userModel.setBirdDate(LocalDate.of(2000, 1, 1));
        userModel.setRolId(1L);

        return userModel;
    }

    public static UserModel getUserOwnerUnderAge() {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setName("Owner");
        userModel.setSurName("Owner");
        userModel.setDni(1234567890);
        userModel.setPhone("+571234567890");
        userModel.setEmail("owner@gmail.com");
        userModel.setPassword("owner123");
        userModel.setBirdDate(LocalDate.of(2020, 1, 1));
        userModel.setRolId(2L);

        return userModel;
    }

    public static UserModel getUserCustomerWithSetters() {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setName("Customer");
        userModel.setSurName("Customer");
        userModel.setDni(1234567890);
        userModel.setPhone("+571234567890");
        userModel.setEmail("customer@gmail.com");
        userModel.setPassword("customer123");
        userModel.setBirdDate(LocalDate.of(2000, 1, 1));
        userModel.setRolId(3L);

        return userModel;
    }


}
