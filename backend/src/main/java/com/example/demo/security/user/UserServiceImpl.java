package com.example.demo.security.user;

//import com.example.demo.security.user.UserRepository;
//import com.example.demo.security.user.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    @Override
    public User save(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User save(User user, Long id) {
        user.setId(id);
        user.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public List<User> findByFirstnameContains(String firstname) {
        return repository.findByFirstNameContains(firstname);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Passwords do not match");
        }

        user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        repository.save(user);
    }
}
