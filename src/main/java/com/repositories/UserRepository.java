package com.repositories;

import com.models.Address;
import com.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByName(String name);

    List<User> findByEmailLike(String email);

    List<User> findByNameQuery();

    List<User> findByEmailQuery();

    @Query(value = "select * from users order by id limit 3", nativeQuery = true)
    List<User> findAllLimit();
}
