package com.example.demo.services;

import com.example.demo.entities.User;

import java.util.List;

public interface UserService {

    User save(User user);
    User save(User user, Long id);
    List<User> findByFirstnameContains(String firstname);
    List<User> findAll();

}
