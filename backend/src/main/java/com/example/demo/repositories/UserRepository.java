package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstNameContains(String keyword);

    // Add this method to find a user by email
    User findByEmail(String email);  // Returns null if no user is found

    // Check if a username already exists in the database
    boolean existsByUsername(String username);

    // Check if an email already exists in the database
    boolean existsByEmail(String email);
}
