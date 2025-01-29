package com.example.demo.services;

import com.example.demo.entities.CurrencyConversion;

import java.util.List;
import java.util.Optional;

public interface CurrencyConversionService {
    List<CurrencyConversion> getAllCurrencyConversions(); // Ajoutez cette méthode
    Optional<CurrencyConversion> getCurrencyConversionById(Long id);
    CurrencyConversion saveCurrencyConversion(CurrencyConversion currencyConversion);
    void deleteCurrencyConversion(Long id);
}
