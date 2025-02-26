package com.example.etapa1DAC.mapper;

import com.example.etapa1DAC.controller.request.CreateEventRequest;
import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.domain.EventDate;

import java.time.LocalDateTime;

public class EventDateMapper {

    public static EventDate toEntity(Event event, String newEventLocation, LocalDateTime startTime, LocalDateTime endTime) {
        return EventDate.builder()
                .event(event)
                .location(newEventLocation)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }

}
