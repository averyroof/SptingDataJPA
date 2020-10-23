package com.repositories;

import com.models.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends PagingAndSortingRepository<Address, Integer> {

    @Query("select adr from Address adr where adr.homeNumber = ?1")
    Address findByHomeQuery(String homeNumber);

    @Query(value = "select * from address adr where adr.city like %?1%", nativeQuery = true)
    List<Address> findByCityQuery(String city);

    @Query("select adr from Address adr where adr.city = :city or adr.street = :street")
    List<Address> findByCityOrStreetQuery(@Param("city") String addressCity,
                                          @Param("street") String street);
}
