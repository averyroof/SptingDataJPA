package com.init;

import com.entity.Address;
import com.entity.User;
import com.services.AddressService;
import com.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com");
        appContext.refresh();

        UserService userService = (UserService) appContext.getBean("userService");
        List<User> allUsers = userService.getAllUsers();
        allUsers.stream().forEach(System.out::println);

        AddressService addressService = (AddressService) appContext.getBean("addressService");
        List<Address> allAdresses = addressService.getAllAddresses();
        allAdresses.stream().forEach(System.out::println);

        appContext.close();
    }
}
