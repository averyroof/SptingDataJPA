package com.init;

import com.clients.AddressClient;
import com.clients.UserClient;
import com.models.Address;
import com.models.User;
import com.services.AddressService;
import com.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com");
        appContext.refresh();

        UserClient userClient = (UserClient)appContext.getBean("userClient");
        userClient.run();

        AddressClient addressClient = (AddressClient) appContext.getBean("addressClient");
        addressClient.run();

        appContext.close();
    }
}
