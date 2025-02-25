package com.example.etapa1DAC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.etapa1DAC.domain.Ticket;
import com.example.etapa1DAC.domain.User;
import com.example.etapa1DAC.repository.TicketRepository;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserService userService;

    public Page<Ticket> myTickets(Pageable pageable){
        User user = this.userService.get();
        return this.ticketRepository.findByOwnerId(user.getId(), pageable);
    }
}
