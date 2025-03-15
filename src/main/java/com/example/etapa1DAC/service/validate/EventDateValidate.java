package com.example.etapa1DAC.service.validate;

import com.example.etapa1DAC.domain.EventWithDates;
import com.example.etapa1DAC.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class EventDateValidate {

    @Autowired
    EventRepository eventRepository;

    public boolean validEventDate(LocalDateTime startTime, LocalDateTime endTime, String location) {

        if (endTime.isBefore(startTime)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O horário de término deve ser após o horário de início.");
        }

        boolean hasOverlappingEvent = eventRepository.existsByLocationAndOverlappingTime(location, startTime, endTime);

        if(hasOverlappingEvent) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento tem horario conflitante");
        }

        return true;
    }

}
