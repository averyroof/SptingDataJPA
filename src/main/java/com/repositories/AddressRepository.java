package com.repositories;

import com.models.Address;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AddressRepository extends PagingAndSortingRepository<Address, Integer> {
    List<Address> findByCity(String city);
}
