package com.example.etapa1DAC.service.search;

import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.domain.TicketType;
import com.example.etapa1DAC.repository.EventRepository;
import com.example.etapa1DAC.repository.TicketRepository;
import com.example.etapa1DAC.repository.TicketTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FindTicketType {

    @Autowired
    TicketTypeRepository ticketTypeRepository;

    public TicketType byId(Long id){
        return ticketTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingresso para esse evento não encontrado"));
    }
}
