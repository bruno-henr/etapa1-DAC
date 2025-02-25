package com.example.etapa1DAC.service;

import com.example.etapa1DAC.DTO.TicketTypeDTO;
import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.domain.TicketType;
import com.example.etapa1DAC.repository.EventRepository;
import com.example.etapa1DAC.repository.TicketRepository;
import com.example.etapa1DAC.repository.TicketTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TicketTypeService {
    @Autowired
    TicketTypeRepository ticketTypeRepository;

    @Autowired
    EventRepository eventRepository;

    public TicketType createTicketType(TicketTypeDTO ticketType) {
        try {

            Event event = eventRepository.findById(ticketType.getEventId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));

            TicketType newTicketType = new TicketType(
                ticketType.name,
                ticketType.totalQuantity,
                ticketType.price,
                ticketType.requiredFields
            );

            newTicketType.setEvent(event);
            return this.ticketTypeRepository.save(newTicketType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TicketType> listTicketType() {
        try {
            return this.ticketTypeRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
