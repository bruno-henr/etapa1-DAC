package com.example.etapa1DAC.mapper;

import com.example.etapa1DAC.controller.request.CreateEventRequest;
import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.domain.EventDate;

public class EventDateMapper {

    public static EventDate toEntity(Event event, CreateEventRequest.EventDateRequest dateRequest) {
        return EventDate.builder()
                .event(event)
                .location(dateRequest.getLocation())
                .startTime(dateRequest.getStartTime())
                .endTime(dateRequest.getEndTime())
                .build();
    }

}
