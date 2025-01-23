package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TaxRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double rate; // Taux de taxe (en pourcentage)

    @Column(nullable = false, unique = true)
    private String zone; // Zone géographique associée au taux de taxe
}
