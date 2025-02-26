package com.example.etapa1DAC.service.search;

import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FindEvent {

    @Autowired
    EventRepository eventRepository;

    public Event byId(Long id){
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento não encontrado"));
    }
}
