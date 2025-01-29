package com.example.demo.serviceImpl;

import com.example.demo.entities.CurrencyConversion;
import com.example.demo.repositories.CurrencyConversionRepository;
import com.example.demo.services.CurrencyConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

    private final CurrencyConversionRepository currencyConversionRepository;

    @Override
    public List<CurrencyConversion> getAllCurrencyConversions() {
        return currencyConversionRepository.findAll();
    }

    @Override
    public Optional<CurrencyConversion> getCurrencyConversionById(Long id) {
        return currencyConversionRepository.findById(id);
    }

    @Override
    public CurrencyConversion saveCurrencyConversion(CurrencyConversion currencyConversion) {
        return currencyConversionRepository.save(currencyConversion);
    }

    @Override
    public void deleteCurrencyConversion(Long id) {
        currencyConversionRepository.deleteById(id);
    }
}
