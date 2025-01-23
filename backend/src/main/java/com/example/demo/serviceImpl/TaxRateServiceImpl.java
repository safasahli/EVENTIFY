package com.example.demo.serviceImpl;

import com.example.demo.entities.TaxRate;
import com.example.demo.repositories.TaxRateRepository;
import com.example.demo.services.TaxRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaxRateServiceImpl implements TaxRateService {

    private final TaxRateRepository taxRateRepository;

    @Override
    public List<TaxRate> findAll() {
        return taxRateRepository.findAll();
    }

    @Override
    public Optional<TaxRate> findById(Long id) {
        return taxRateRepository.findById(id);
    }

    @Override
    public TaxRate save(TaxRate taxRate) {
        return taxRateRepository.save(taxRate);
    }

    @Override
    public void deleteById(Long id) {
        taxRateRepository.deleteById(id);
    }
}
