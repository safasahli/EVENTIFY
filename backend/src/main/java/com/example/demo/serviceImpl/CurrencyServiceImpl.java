package com.example.demo.serviceImpl;

import com.example.demo.entities.Currency;
import com.example.demo.repositories.CurrencyRepository;
import com.example.demo.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    @Override
    public Optional<Currency> getCurrencyById(Long id) {
        return currencyRepository.findById(id); // Retourne un Optional
    }

    @Override
    public Currency saveCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public void deleteCurrency(Long id) {
        currencyRepository.deleteById(id);
    }
}
