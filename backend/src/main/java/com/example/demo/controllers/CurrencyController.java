package com.example.demo.controllers;

import com.example.demo.entities.Currency;
import com.example.demo.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    // Récupérer toutes les devises
    @GetMapping
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        List<Currency> currencies = currencyService.getAllCurrencies();
        return ResponseEntity.ok(currencies);
    }

    // Récupérer une devise par ID
    @GetMapping("/{id}")
    public ResponseEntity<Currency> getCurrencyById(@PathVariable Long id) {
        return currencyService.getCurrencyById(id)
                .map(ResponseEntity::ok) // Transforme Optional en ResponseEntity
                .orElse(ResponseEntity.notFound().build()); // Gestion des cas où la devise n'existe pas
    }

    // Créer ou mettre à jour une devise
    @PostMapping
    public ResponseEntity<Currency> createOrUpdateCurrency(@RequestBody Currency currency) {
        Currency savedCurrency = currencyService.saveCurrency(currency);
        return ResponseEntity.ok(savedCurrency);
    }

    // Supprimer une devise par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Long id) {
        currencyService.deleteCurrency(id);
        return ResponseEntity.noContent().build();
    }
}
