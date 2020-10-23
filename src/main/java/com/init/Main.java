package com.init;

import com.clients.AddressClient;
import com.clients.UserClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void run() throws IOException {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com");
        appContext.refresh();

        UserClient userClient = (UserClient) appContext.getBean("userClient");
        AddressClient addressClient = (AddressClient) appContext.getBean("addressClient");

        boolean boolC = true;
        while (boolC) {
            System.out.println("\nВыберите одну из следующих таблиц: ");
            System.out.println("1 - Пользователи");
            System.out.println("2 - Адреса");
            System.out.print("Номер таблицы: ");
            String choice = bf.readLine();

            switch (choice) {
                case "1":
                    userClient.run();
                    break;

                case "2":
                    addressClient.run();
                    break;
            }
        }

        appContext.close();
    }

    public static void main(String[] args) throws IOException {
        run();
    }
}
