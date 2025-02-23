package com.example.etapa1DAC;

import com.example.etapa1DAC.DTO.EventWithDatesDTO;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Utils {
    public static EventWithDatesDTO mapObjectArrayToEventWithDatesDTO(Object[] tuple) {
        Long eventId = (Long) tuple[0];  // eventId
        String eventName = (String) tuple[1];  // eventName
        String eventDescription = (String) tuple[2];  // eventDescription
        String eventLocation = (String) tuple[3];  // eventLocation
        Long eventDateId = (Long) tuple[4];  // eventDateId
        LocalDateTime startTime = ((Timestamp) tuple[5]).toLocalDateTime();  // startTime
        LocalDateTime endTime = ((Timestamp) tuple[6]).toLocalDateTime();  // endTime

        // Criando e retornando o DTO
        return new EventWithDatesDTO(
                eventId,
                eventName,
                eventDescription,
                eventLocation,
                eventDateId,
                startTime,
                endTime
        );
    }
}
