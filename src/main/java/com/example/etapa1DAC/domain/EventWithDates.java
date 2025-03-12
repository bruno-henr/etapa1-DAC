package com.example.etapa1DAC.domain;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventWithDates {
    private Long eventId;
    private String eventName;
    private String eventDescription;
    private String eventLocation;
    private Integer eventCapacity;
    private Double eventPrice;
    private Long eventDateId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public EventWithDates(Long eventId, String eventName, String eventDescription, String eventLocation, Long eventDateId, LocalDateTime startTime, LocalDateTime endTime) {
    }
}