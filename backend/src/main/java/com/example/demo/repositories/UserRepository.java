package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    //select * from users
    List<User> findAll();

    //select * from users where firstname starts with s.thing
    List<User> findByFirstnameContains(String firstname);
}
