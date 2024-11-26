package com.example.etapa1DAC.DTO;

import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class EventWithDatesDTO {
    private Long eventId;
    private String eventName;
    private String eventDescription;
    private String eventLocation;
    private Integer eventCapacity;
    private Double eventPrice;
    private Long eventDateId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public EventWithDatesDTO(
            Long eventId,
            String eventName,
            String eventDescription,
            String eventLocation,
            Integer eventCapacity,
            Double eventPrice,
            Long eventDateId,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.eventCapacity = eventCapacity;
        this.eventPrice = eventPrice;
        this.eventDateId = eventDateId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}