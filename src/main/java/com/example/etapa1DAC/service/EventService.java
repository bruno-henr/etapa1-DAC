package com.example.etapa1DAC.service;

import com.example.etapa1DAC.DTO.CreateEventDTO;
import com.example.etapa1DAC.DTO.EventWithDatesDTO;
import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.domain.EventDate;
import com.example.etapa1DAC.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventDateService  eventDateService;

    public boolean validEventDate(LocalDateTime startTime, LocalDateTime endTime, String location) {
        EventWithDatesDTO eventFind = eventRepository.findEventsAndDatesByLocation(location);

        if (eventFind.getEventId() == null) {
            return true;
        }

        return endTime.isBefore(eventFind.getStartTime()) || startTime.isAfter(eventFind.getEndTime());
    }

    public Event createEvent(CreateEventDTO newEvent) {
        try {
            Event event = new Event(
                newEvent.name,
                newEvent.description,
                newEvent.category
            );
            eventRepository.save(event);
            if (newEvent.start_time != null && newEvent.end_time != null && newEvent.location != null) {
                EventDate eventDate = new EventDate(
                        event,
                        newEvent.location,
                        LocalDateTime.parse(newEvent.start_time),
                        LocalDateTime.parse(newEvent.end_time)
                );
                eventDateService.createEventDate(eventDate);
            }

            return event;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Event> listEvents() {
        return this.eventRepository.findAll();
    }
}
