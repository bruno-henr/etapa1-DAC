package com.example.etapa1DAC.repository;

import com.example.etapa1DAC.domain.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
}
