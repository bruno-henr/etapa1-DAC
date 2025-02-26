package com.example.etapa1DAC.service.validate;

import com.example.etapa1DAC.domain.EventWithDates;
import com.example.etapa1DAC.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventDateValidate {

    @Autowired
    EventRepository eventRepository;

    public boolean validEventDate(LocalDateTime startTime, LocalDateTime endTime, String location) {
        EventWithDates eventFind = eventRepository.findEventsAndDatesByLocation(location);

        if (eventFind.getEventId() == null) {
            return true;
        }

        return endTime.isBefore(eventFind.getStartTime()) || startTime.isAfter(eventFind.getEndTime());
    }

}
