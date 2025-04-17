package com.example.demo.security.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    // Load user by username (email)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("🔍 Looking up user by email: " + email);
        // Fetch user from the database using the email
        User user = userRepository.findByEmail(email);

        // If user is not found, throw UsernameNotFoundException
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with email: " + email);
        }

        // Return the UserDetailsImpl object
        return UserDetailsImpl.build(user);
    }
}
