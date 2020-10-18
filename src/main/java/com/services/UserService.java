package com.services;

import com.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("userService")
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User getById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void deleteUser(User adr) {
        userRepository.delete(adr);
    }

    @Transactional
    public boolean addUser(User user) {
        userRepository.save(user);
        return true;
    }

    @Transactional
    public boolean updateUser(User user) {
        userRepository.save(user);
        return true;
    }
}
