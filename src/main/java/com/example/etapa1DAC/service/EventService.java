package com.example.etapa1DAC.service;

import com.example.etapa1DAC.DTO.CreateEventDTO;
import com.example.etapa1DAC.DTO.EventWithDatesDTO;
import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.domain.EventDate;
import com.example.etapa1DAC.exceptions.EventDateConflictException;
import com.example.etapa1DAC.repository.EventRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
            LocalDateTime startTime = LocalDateTime.parse(newEvent.start_time);
            LocalDateTime endTime = LocalDateTime.parse(newEvent.end_time);
            if(!this.validEventDate(startTime, endTime, newEvent.location)) {
                throw new EventDateConflictException();
            }
            eventRepository.save(event);
            if (newEvent.start_time != null && newEvent.end_time != null && newEvent.location != null) {
                EventDate eventDate = new EventDate(
                        event,
                        newEvent.location,
                        startTime,
                        endTime
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

    public Page<Event> filterEvents(String category, String location, LocalDateTime startTime, LocalDateTime endTime, Pageable pageable) {
        Specification<Event> spec = withFilters(category, location, startTime, endTime);
        return this.eventRepository.findAll(spec, pageable);
    }

    private Specification<Event> withFilters(String category, String location, LocalDateTime startTime, LocalDateTime endTime) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (category != null) {
                predicates.add(cb.equal(root.get("category"), category));
            }

            if (location != null) {
                Join<Event, EventDate> eventDateJoin = root.join("eventDates");
                predicates.add(cb.equal(eventDateJoin.get("location"), location));
            }

            if (startTime != null) {
                Join<Event, EventDate> eventDateJoin = root.join("eventDates");
                predicates.add(cb.greaterThanOrEqualTo(eventDateJoin.get("start_time"), startTime));
            }

            if(endTime != null) {
                Join<Event, EventDate> eventDateJoin = root.join("eventDates");
                predicates.add(cb.lessThanOrEqualTo(eventDateJoin.get("end_time"), startTime));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
