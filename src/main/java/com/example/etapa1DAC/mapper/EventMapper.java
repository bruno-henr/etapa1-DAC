package com.example.etapa1DAC.mapper;

import com.example.etapa1DAC.controller.request.CreateEventRequest;
import com.example.etapa1DAC.domain.Event;

public class EventMapper {

    public static Event toEntity(CreateEventRequest eventRequest) {
        return Event.builder()
                .name(eventRequest.name)
                .description(eventRequest.description)
                .category(eventRequest.category)
                .build();
    }

}
