package com.example.demo.controllers;

import com.example.demo.entities.TaxRate;
import com.example.demo.services.TaxRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taxrates")
@RequiredArgsConstructor
public class TaxRateController {

    private final TaxRateService taxRateService;

    // Récupérer tous les taux de taxe
    @GetMapping
    public ResponseEntity<List<TaxRate>> getAllTaxRates() {
        List<TaxRate> taxRates = taxRateService.findAll();
        return ResponseEntity.ok(taxRates);
    }

    // Récupérer un taux de taxe par ID
    @GetMapping("/{id}")
    public ResponseEntity<TaxRate> getTaxRateById(@PathVariable Long id) {
        return taxRateService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Créer un nouveau taux de taxe
    @PostMapping
    public ResponseEntity<TaxRate> createTaxRate(@RequestBody TaxRate taxRate) {
        TaxRate createdTaxRate = taxRateService.save(taxRate);
        return ResponseEntity.ok(createdTaxRate);
    }

    // Mettre à jour un taux de taxe existant
    @PutMapping("/{id}")
    public ResponseEntity<TaxRate> updateTaxRate(@PathVariable Long id, @RequestBody TaxRate taxRateDetails) {
        return taxRateService.findById(id)
                .map(existingTaxRate -> {
                    existingTaxRate.setRate(taxRateDetails.getRate());
                    existingTaxRate.setZone(taxRateDetails.getZone());
                    TaxRate updatedTaxRate = taxRateService.save(existingTaxRate);
                    return ResponseEntity.ok(updatedTaxRate);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Supprimer un taux de taxe
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaxRate(@PathVariable Long id) {
        if (taxRateService.findById(id).isPresent()) {
            taxRateService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
