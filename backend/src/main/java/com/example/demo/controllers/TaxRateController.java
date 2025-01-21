package com.example.demo.controllers;

import com.example.demo.entities.TaxRate;
import com.example.demo.services.TaxRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tax-rates")
@RequiredArgsConstructor
public class TaxRateController {
    private final TaxRateService taxRateService;

    @GetMapping
    public ResponseEntity<List<TaxRate>> getAllTaxRates() {
        List<TaxRate> taxRates = taxRateService.getAllTaxRates();
        return ResponseEntity.ok(taxRates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaxRate> getTaxRateById(@PathVariable Long id) {
        TaxRate taxRate = taxRateService.getTaxRateById(id);
        return ResponseEntity.ok(taxRate);
    }

    @PostMapping
    public ResponseEntity<TaxRate> createTaxRate(@RequestBody TaxRate taxRate) {
        TaxRate createdTaxRate = taxRateService.createTaxRate(taxRate);
        return ResponseEntity.ok(createdTaxRate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaxRate> updateTaxRate(@PathVariable Long id, @RequestBody TaxRate taxRate) {
        TaxRate updatedTaxRate = taxRateService.updateTaxRate(id, taxRate);
        return ResponseEntity.ok(updatedTaxRate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaxRate(@PathVariable Long id) {
        taxRateService.deleteTaxRate(id);
        return ResponseEntity.noContent().build();
    }
}
