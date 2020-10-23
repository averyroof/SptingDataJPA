package com.clients;

import com.init.Main;
import com.models.Address;
import com.models.User;
import com.repositories.AddressRepository;
import com.repositories.UserRepository;
import com.services.UserService;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component("userClient")
public class UserClient {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private Main m = new Main();

    public UserClient(UserService userService, UserRepository userRepository, AddressRepository addressRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    // findAll and count (CRUD)
    public void findingAllUsers() {
        System.out.println("\n -- finding all users --");
        System.out.println("Количество пользователей: " + userRepository.count());
        for (User us : userRepository.findAll()) System.out.println(us);
    }

    // save (CRUD)
    public void addNewUser() throws IOException {
        System.out.println("\n -- add new user --");
        System.out.println("Введите имя пользователя: ");
        String newName = bf.readLine();
        System.out.println("Введите email пользователя: ");
        String newEmail = bf.readLine();
        System.out.println("Введите id адреса пользователя: ");
        int newIdAddress = Integer.parseInt(bf.readLine());
        // findById (CRUD)
        Address adrById = addressRepository.findById(newIdAddress).get();
        User newUser = new User();
        newUser.setName(newName);
        newUser.setEmail(newEmail);
        newUser.setAddressForUser(adrById);
        userRepository.save(newUser);
    }

    // deleteById (CRUD)
    public void deleteUserById() throws IOException {
        System.out.println("\n -- delete user --");
        System.out.println("Введите id удаляемого пользователя: ");
        int delId = Integer.parseInt(bf.readLine());
        userRepository.deleteById(delId);
    }

    // existsById (CRUD)
    public void existsByIdUser() throws IOException {
        System.out.println("\nВведите id пользователя: ");
        int idUser = Integer.parseInt(bf.readLine());
        if (userRepository.existsById(idUser)) System.out.println("Пользователь существует");
        else System.out.println("Пользователь не существует");
    }

    // query builder mechanism
    public void findByName() throws IOException {
        System.out.println("\nВведите имя пользователя: ");
        String nameUser = bf.readLine();
        System.out.println(userRepository.findByName(nameUser));
    }

    // query builder mechanism
    public void findByEmailLike() throws IOException {
        System.out.println("\nВведите email пользователя (с процентами в начале и в конце): ");
        String email = bf.readLine();
        userRepository.findByEmailLike(email).stream().forEach(System.out::println);
    }

    // NamedQuery
    public void findByNameQuery() {
        userRepository.findByNameQuery().stream().forEach(System.out::println);
    }

    // NamedQuery
    public void findByEmailQuery() {
        userRepository.findByEmailQuery().stream().forEach(System.out::println);
    }

    // Query
    public void findAllLimit() {
        userRepository.findAllLimit().stream().forEach(System.out::println);
    }

    public void run() throws IOException {

        boolean boolC = true;
        while (boolC) {
            System.out.println("\nВыберите действие: ");
            System.out.println("1 - найти всех пользователей");
            System.out.println("2 - добавить нового пользователя");
            System.out.println("3 - удалить пользователя");
            System.out.println("4 - проверка на существование пользователя по id");
            System.out.println("5 - найти по имени (используя query builder mechanism)");
            System.out.println("6 - найти пользователей по введенному email (используя query builder mechanism)");
            System.out.println("7 - найти пользователей с именами \"Tom\" либо \"Emily\" (NamedQuery)");
            System.out.println("8 - найти пользователей с email содержащим букву \"j\" (NamedQuery)");
            System.out.println("9 - найти всех пользователей (limit))");
            System.out.println("0 - вернуться назад");
            String choice = bf.readLine();
            switch (choice) {
                case "1":
                    findingAllUsers();
                    break;
                case "2":
                    addNewUser();
                    break;
                case "3":
                    deleteUserById();
                    break;
                case "4":
                    existsByIdUser();
                    break;
                case "5":
                    findByName();
                    break;
                case "6":
                    findByEmailLike();
                    break;
                case "7":
                    findByNameQuery();
                    break;
                case "8":
                    findByEmailQuery();
                    break;
                case "9":
                    findAllLimit();
                    break;
                case "0":
                    m.run();
                    break;
            }
        }
    }
}
