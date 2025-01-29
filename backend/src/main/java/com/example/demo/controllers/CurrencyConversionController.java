package com.example.demo.controllers;

import com.example.demo.entities.CurrencyConversion;
import com.example.demo.services.CurrencyConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currency-conversions")
@RequiredArgsConstructor
public class CurrencyConversionController {

    private final CurrencyConversionService currencyConversionService;

    // Récupérer toutes les conversions de devises
    @GetMapping
    public ResponseEntity<List<CurrencyConversion>> getAllCurrencyConversions() {
        List<CurrencyConversion> currencyConversions = currencyConversionService.getAllCurrencyConversions();
        return ResponseEntity.ok(currencyConversions);
    }

    // Récupérer une conversion de devise par ID
    @GetMapping("/{id}")
    public ResponseEntity<CurrencyConversion> getCurrencyConversionById(@PathVariable Long id) {
        return currencyConversionService.getCurrencyConversionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Créer ou mettre à jour une conversion de devise
    @PostMapping
    public ResponseEntity<CurrencyConversion> createOrUpdateCurrencyConversion(@RequestBody CurrencyConversion currencyConversion) {
        CurrencyConversion savedCurrencyConversion = currencyConversionService.saveCurrencyConversion(currencyConversion);
        return ResponseEntity.ok(savedCurrencyConversion);
    }

    // Supprimer une conversion de devise par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrencyConversion(@PathVariable Long id) {
        currencyConversionService.deleteCurrencyConversion(id);
        return ResponseEntity.noContent().build();
    }
}
