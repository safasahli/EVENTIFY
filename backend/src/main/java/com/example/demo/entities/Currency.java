package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String symbol;

   /* @OneToMany(mappedBy = "currency")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "defaultCurrency")
    private List<Store> stores;

    @OneToMany(mappedBy = "fromCurrency")
    private List<CurrencyConversion> fromConversions;

    @OneToMany(mappedBy = "toCurrency")
    private List<CurrencyConversion> toConversions;*/
}
