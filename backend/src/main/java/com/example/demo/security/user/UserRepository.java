package com.example.demo.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstNameContains(String firstName);

    // 🔹 Chercher un utilisateur par email
    Optional<User> findByEmail(String email);

    // 🔹 Vérifier si un utilisateur existe par email
    boolean existsByEmail(String email);
}
