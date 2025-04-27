package com.example.demo.repositories;

import com.example.demo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDateTime;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);  // Recherche des commandes par utilisateur
   // List<Order> findByStatus(String status);  // Recherche des commandes par statut
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);  // Recherche des commandes dans une plage de dates
}