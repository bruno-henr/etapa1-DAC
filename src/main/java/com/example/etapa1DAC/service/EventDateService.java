package com.example.etapa1DAC.service;

import com.example.etapa1DAC.domain.EventDate;
import com.example.etapa1DAC.repository.EventDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventDateService {
    @Autowired
    EventDateRepository eventDateRepository;

    public EventDate createEventDate(EventDate eventDate) {
        try {
            return eventDateRepository.save(eventDate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
