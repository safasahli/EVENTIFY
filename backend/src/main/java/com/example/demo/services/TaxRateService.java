package com.example.demo.services;

import com.example.demo.entities.TaxRate;
import com.example.demo.repositories.TaxRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaxRateService {
    private final TaxRateRepository taxRateRepository;

    public List<TaxRate> getAllTaxRates() {
        return taxRateRepository.findAll();
    }

    public TaxRate getTaxRateById(Long id) {
        return taxRateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaxRate not found with id: " + id));
    }

    public TaxRate createTaxRate(TaxRate taxRate) {
        return taxRateRepository.save(taxRate);
    }

    public TaxRate updateTaxRate(Long id, TaxRate updatedTaxRate) {
        TaxRate existingTaxRate = getTaxRateById(id);
        existingTaxRate.setRate(updatedTaxRate.getRate());
        return taxRateRepository.save(existingTaxRate);
    }

    public void deleteTaxRate(Long id) {
        taxRateRepository.deleteById(id);
    }
}
