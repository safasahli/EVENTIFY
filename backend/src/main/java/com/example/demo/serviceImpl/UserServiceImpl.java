package com.example.demo.serviceImpl;

import com.example.demo.security.user.User;
import com.example.demo.security.user.UserRepository;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User save(User user, Long id) {
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public List<User> findByFirstnameContains(String firstname) {
        return userRepository.findByFirstNameContains(firstname);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
