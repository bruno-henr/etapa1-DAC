package com.example.etapa1DAC.mapper;

import com.example.etapa1DAC.controller.request.CreateEventRequest;
import com.example.etapa1DAC.domain.CategoryEventType;
import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.domain.EventDate;
import com.example.etapa1DAC.domain.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EventMapper {

    public static Event toEntity(CreateEventRequest eventRequest, Set<CategoryEventType> categories, User owner) {
        Event event = Event.builder()
                .owner(owner)
                .name(eventRequest.getName())
                .description(eventRequest.getDescription())
                .categories(categories)
                .maxCapacity(eventRequest.getMaxCapacity())
                .location(eventRequest.getLocation())
                .startTime(eventRequest.getStartTime())
                .endTime(eventRequest.getEndTime())
                .dates(new HashSet<>())
                .build();

        if (eventRequest.getDates() != null) {
            Set<EventDate> eventDates = eventRequest.getDates().stream()
                    .map(dateRequest -> EventDateMapper.toEntity(event, dateRequest)) // ← Novo método no EventDateMapper
                    .collect(Collectors.toSet());
            event.setDates(eventDates);
        }

        return event;

    }

}
