package com.services;

import com.models.User;
import com.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("userService")
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
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
