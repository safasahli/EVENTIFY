package com.example.demo.entities;

import com.example.demo.security.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content; // Contenu de l'avis

    private Integer rating; // Note attribuée (par exemple, de 1 à 5)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Auteur de l'avis

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Produit concerné par l'avis

    private LocalDateTime createdAt; // Date de création de l'avis

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
