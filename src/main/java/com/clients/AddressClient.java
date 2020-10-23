package com.clients;

import com.init.Main;
import com.models.Address;
import com.repositories.AddressRepository;
import com.services.AddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component("addressClient")
public class AddressClient {

    private final AddressService addressService;
    private final AddressRepository addressRepository;
    public static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private Main m = new Main();

    public AddressClient(AddressService addressService, AddressRepository addressRepository) {
        this.addressService = addressService;
        this.addressRepository = addressRepository;
    }

    // findAll and count (CRUD)
    public void findingAllAddresses() {
        System.out.println("\n -- finding all addresses --");
        System.out.println("Количество адресов: " + addressRepository.count());
        for (Address adr : addressRepository.findAll()) System.out.println(adr);
    }

    // save (CRUD)
    public void addNewAddress() throws IOException {
        System.out.println("\n -- add new address --");
        System.out.println("Введите город: ");
        String newCity = bf.readLine();
        System.out.println("Введите улицу: ");
        String newStreet = bf.readLine();
        System.out.println("Введите номер дома: ");
        String newNumber = bf.readLine();
        Address newAddress = new Address();
        newAddress.setCity(newCity);
        newAddress.setStreet(newStreet);
        newAddress.setHomeNumber(newNumber);
        addressRepository.save(newAddress);
    }

    // deleteById (CRUD)
    public void deleteAddressById() throws IOException {
        System.out.println("\n -- delete address --");
        System.out.println("Введите id удаляемого адреса: ");
        int delId = Integer.parseInt(bf.readLine());
        addressRepository.deleteById(delId);
    }

    // Query
    public void findByHomeQuery() throws IOException {
        System.out.println("\nВведите номер дома: ");
        String number = bf.readLine();
        System.out.println(addressRepository.findByHomeQuery(number));
    }

    // Query
    public void findByCityQuery() throws IOException {
        System.out.println("\nВведите город: ");
        String city = bf.readLine();
        System.out.println(addressRepository.findByCityQuery(city));
    }

    // Query
    public void findByCityOrStreetQuery() throws IOException {
        System.out.println("\nВведите город: ");
        String city = bf.readLine();
        System.out.println("Введите улицу: ");
        String street = bf.readLine();
        addressRepository.findByCityOrStreetQuery(city, street).stream().forEach(System.out::println);
    }

    // Pagination and Sorting (PagingAndSortingRepository)
    public void paginationAndSorting() throws IOException {
        System.out.println("\n -- pagination --");
        System.out.println("Введите номер страницы: ");
        int page = Integer.parseInt(bf.readLine());
        Pageable firstPageWithTwoElements = PageRequest.of(page-1, 2);
        Page<Address> allAddresses = addressRepository.findAll(firstPageWithTwoElements);
        System.out.println("Всего страниц: " + allAddresses.getTotalPages());
        allAddresses.stream().forEach(System.out::println);
        System.out.println("");

        System.out.println("Сортировка по городу (возрастание) и по улице (убывание): ");
        for (Address adr : addressRepository.findAll(Sort.by("city").ascending().and(Sort.by("street").descending()))) {
            System.out.println(adr);
        }
        System.out.println("");

        System.out.println("Пагинация (первые 6 позиции) и сортировка по улице (убывание): ");
        Pageable sorted = PageRequest.of(0, 6, Sort.by("street").descending());
        Page<Address> sortedByStreet = addressRepository.findAll(sorted);
        sortedByStreet.stream().forEach(System.out::println);
    }

    public void run() throws IOException {

        boolean boolC = true;
        while (boolC) {
            System.out.println("\nВыберите действие: ");
            System.out.println("1 - найти все адреса");
            System.out.println("2 - добавить новый адрес");
            System.out.println("3 - удалить адрес");
            System.out.println("4 - найти адрес по введенному дому");
            System.out.println("5 - найти адрес по введенному (неполному) городу ");
            System.out.println("6 - найти адрес по введенному городу или по улице");
            System.out.println("7 - пагинация и сортировка");
            System.out.println("0 - вернуться назад");
            String choice = bf.readLine();
            switch (choice) {
                case "1":
                    findingAllAddresses();
                    break;
                case "2":
                    addNewAddress();
                    break;
                case "3":
                    deleteAddressById();
                    break;
                case "4":
                    findByHomeQuery();
                    break;
                case "5":
                    findByCityQuery();
                    break;
                case "6":
                    findByCityOrStreetQuery();
                    break;
                case "7":
                    paginationAndSorting();
                    break;
                case "0":
                    m.run();
                    break;
            }
        }
    }
}
