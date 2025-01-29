package com.example.demo.repositories;

import com.example.demo.entities.CurrencyConversion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversion, Long> {
}
