package com.example.etapa1DAC;

import com.example.etapa1DAC.DTO.EventWithDatesDTO;

import java.time.LocalDateTime;

public class Utils {
    public static EventWithDatesDTO mapObjectArrayToEventWithDatesDTO(Object[] tuple) {
        Long eventId = (Long) tuple[0];  // eventId
        String eventName = (String) tuple[1];  // eventName
        String eventDescription = (String) tuple[2];  // eventDescription
        String eventLocation = (String) tuple[3];  // eventLocation
        Integer eventCapacity = (Integer) tuple[4];  // eventCapacity
        Double eventPrice = (Double) tuple[5];  // eventPrice
        Long eventDateId = (Long) tuple[6];  // eventDateId
        LocalDateTime startTime = (LocalDateTime) tuple[7];  // startTime
        LocalDateTime endTime = (LocalDateTime) tuple[8];  // endTime

        // Criando e retornando o DTO
        return new EventWithDatesDTO(
                eventId,
                eventName,
                eventDescription,
                eventLocation,
                eventCapacity,
                eventPrice,
                eventDateId,
                startTime,
                endTime
        );
    }
}
