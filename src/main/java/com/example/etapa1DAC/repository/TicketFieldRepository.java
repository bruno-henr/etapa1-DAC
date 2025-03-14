package com.example.etapa1DAC.repository;

import com.example.etapa1DAC.domain.TicketField;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TicketFieldRepository extends JpaRepository<TicketField, Long> {

}
