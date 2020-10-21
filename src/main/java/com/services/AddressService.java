package com.services;

import com.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repositories.AddressRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service("addressService")
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Transactional
    public List<Address> getAllAddresses() {
        return (List<Address>) addressRepository.findAll();
    }

    @Transactional
    public List<Address> findByCity(String city) {
        return addressRepository.findByCity(city);
    }

    @Transactional
    public Address getById(Integer id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteAddressById(Integer id) {
        addressRepository.deleteById(id);
    }

    @Transactional
    public void deleteAddress(Address adr) {
        addressRepository.delete(adr);
    }

    @Transactional
    public boolean addAddress(Address address) {
        addressRepository.save(address);
        return true;
    }

    @Transactional
    public boolean updateAddress(Address address) {
        addressRepository.save(address);
        return true;
    }
}
