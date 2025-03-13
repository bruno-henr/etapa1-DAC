package com.example.etapa1DAC.repository;

import com.example.etapa1DAC.domain.CategoryEventType;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CategoryEventTypeRepository extends JpaRepository<CategoryEventType, Long> {

}
