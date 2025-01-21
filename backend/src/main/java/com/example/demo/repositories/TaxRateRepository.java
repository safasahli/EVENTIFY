package com.example.demo.repositories;



import com.example.demo.entities.TaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxRateRepository extends JpaRepository<TaxRate, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
