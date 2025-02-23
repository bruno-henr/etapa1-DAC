package com.example.etapa1DAC.service;

import com.example.etapa1DAC.DTO.TicketTypeDTO;
import com.example.etapa1DAC.domain.TicketType;
import com.example.etapa1DAC.repository.TicketTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TicketTypeService {
    @Autowired
    TicketTypeRepository ticketTypeRepository;

    public TicketType createTicketType(TicketTypeDTO ticketType) {
        try {
            TicketType newTicketType = new TicketType(
                ticketType.name,
                ticketType.totalQuantity,
                ticketType.price,
                ticketType.requiredFields
            );
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
