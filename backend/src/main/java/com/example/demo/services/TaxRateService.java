package com.example.demo.services;

import com.example.demo.entities.TaxRate;

import java.util.List;
import java.util.Optional;

public interface TaxRateService {
    List<TaxRate> findAll();
    Optional<TaxRate> findById(Long id);
    TaxRate save(TaxRate taxRate);
    void deleteById(Long id);
}
