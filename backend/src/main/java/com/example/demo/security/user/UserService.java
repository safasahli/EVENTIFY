package com.example.demo.security.user;

import java.security.Principal;
import java.util.List;

public interface UserService {
    User save(User user);
    User save(User user, Long id);
    List<User> findByFirstnameContains(String firstName);
    List<User> findAll();
    void changePassword(ChangePasswordRequest request, Principal connectedUser);
}
