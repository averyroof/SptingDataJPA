package com.services;

import com.models.Address;
import com.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("addressService")
public class AddressService {

    final
    AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public List<Address> getAllAddresses() {
        return (List<Address>) addressRepository.findAll();
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
