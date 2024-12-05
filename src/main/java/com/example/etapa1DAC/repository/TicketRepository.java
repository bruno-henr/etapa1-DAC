package com.example.etapa1DAC.repository;

import com.example.etapa1DAC.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
