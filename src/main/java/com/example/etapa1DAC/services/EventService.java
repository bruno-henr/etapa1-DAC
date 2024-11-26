package com.example.etapa1DAC.services;

import com.example.etapa1DAC.DTO.EventWithDatesDTO;
import com.example.etapa1DAC.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public boolean validEventDate(LocalDateTime startTime, LocalDateTime endTime, String location) {
        EventWithDatesDTO eventFind = eventRepository.findEventsAndDatesByLocation(location);

        if (eventFind.getEventId() == null) {
            System.out.println("PASSOU");
            return true;
        }

        return endTime.isBefore(eventFind.getStartTime()) || startTime.isAfter(eventFind.getEndTime());
    }
}
