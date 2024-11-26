package com.example.etapa1DAC.repositories;

import com.example.etapa1DAC.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
