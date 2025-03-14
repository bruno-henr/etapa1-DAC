package com.example.etapa1DAC.mapper;

import com.example.etapa1DAC.domain.EventWithDates;

import java.time.LocalDateTime;

public class EventWithDateMapper {

    public static EventWithDates mapToEventWithDates(Object[] tuple) {
        EventWithDates dto = new EventWithDates();
        dto.setEventId((Long) tuple[0]);
        dto.setEventName((String) tuple[1]);
        dto.setEventDescription((String) tuple[2]);
        dto.setEventLocation((String) tuple[3]);
        dto.setEventDateId((Long) tuple[4]);
        dto.setStartTime((LocalDateTime) tuple[5]);
        dto.setEndTime((LocalDateTime) tuple[6]);
        return dto;
    }

}
