package com.example.demo.controllers;

import com.example.demo.security.user.User;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService UserService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(UserService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        return ResponseEntity.ok(UserService.save(user, id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchByFirstname(@RequestParam String firstname) {
        return ResponseEntity.ok(UserService.findByFirstnameContains(firstname));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(UserService.findAll());
    }
}