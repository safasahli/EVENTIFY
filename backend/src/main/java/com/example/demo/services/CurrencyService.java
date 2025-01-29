package com.example.demo.services;

import com.example.demo.entities.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    List<Currency> getAllCurrencies();
    Optional<Currency> getCurrencyById(Long id); // Utiliser Optional ici
    Currency saveCurrency(Currency currency);
    void deleteCurrency(Long id);
}
