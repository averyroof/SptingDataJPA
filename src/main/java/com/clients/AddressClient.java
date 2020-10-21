package com.clients;

import com.models.Address;
import com.models.User;
import com.services.AddressService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("addressClient")
public class AddressClient {

    private final AddressService addressService;

    public AddressClient(AddressService addressService) {
        this.addressService = addressService;
    }

    public void run() {
        System.out.println("\n -- finding all addresses --");
        List<Address> allAddresses = addressService.getAllAddresses();
        allAddresses.stream().forEach(System.out::println);
    }
}
