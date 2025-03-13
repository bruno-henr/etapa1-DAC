package com.example.etapa1DAC.repository;

import com.example.etapa1DAC.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
