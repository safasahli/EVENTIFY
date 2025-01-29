package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CurrencyConversion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_currency_id")
    private Currency fromCurrency;

    @ManyToOne
    @JoinColumn(name = "to_currency_id")
    private Currency toCurrency;

    private Double rate;
}
