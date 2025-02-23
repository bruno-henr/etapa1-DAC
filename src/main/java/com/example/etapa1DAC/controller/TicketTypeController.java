package com.example.etapa1DAC.controller;

import com.example.etapa1DAC.DTO.TicketTypeDTO;
import com.example.etapa1DAC.domain.TicketType;
import com.example.etapa1DAC.service.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket/type")
public class TicketTypeController {
    @Autowired
    private TicketTypeService ticketTypeService;

    @PostMapping("/create")
    TicketType createTicketType(@RequestBody TicketTypeDTO ticketType) {
        try {
            return this.ticketTypeService.createTicketType(ticketType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/list")
    List<TicketType> createTicketType() {
        try {
            return this.ticketTypeService.listTicketType();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
