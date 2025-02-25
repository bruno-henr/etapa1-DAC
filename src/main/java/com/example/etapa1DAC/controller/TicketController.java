package com.example.etapa1DAC.controller;

import com.example.etapa1DAC.domain.User;
import com.example.etapa1DAC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.etapa1DAC.domain.Ticket;
import com.example.etapa1DAC.service.TicketService;

@RestController
@RequestMapping("/ticket/")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;

    @GetMapping
    Page<Ticket> myTickets(Pageable pageable) {
        User user = userService.getAuthenticatedUser();
        return ticketService.myTickets(user.getId(), pageable);
    }
}
