package com.clients;

import com.models.User;
import com.services.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userClient")
public class UserClient {

    private final UserService userService;

    public UserClient(UserService userService) {
        this.userService = userService;
    }

    public void run() {
        System.out.println("\n -- finding all users --");
        List<User> allUsers = userService.getAllUsers();
        allUsers.stream().forEach(System.out::println);
    }
}
