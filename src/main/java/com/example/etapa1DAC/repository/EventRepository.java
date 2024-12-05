package com.example.etapa1DAC.repository;

import com.example.etapa1DAC.DTO.EventWithDatesDTO;
import com.example.etapa1DAC.Utils;
import com.example.etapa1DAC.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(
            value = "SELECT e.id AS eventId, " +
                    "e.name AS eventName, " +
                    "e.description AS eventDescription, " +
                    "e.location AS eventLocation, " +
                    "e.capacity AS eventCapacity, " +
                    "e.price AS eventPrice, " +
                    "ed.id AS eventDateId, " +
                    "ed.start_time AS startTime, " +
                    "ed.end_time AS endTime " +
                    "FROM event e " +
                    "LEFT JOIN event_date ed ON e.id = ed.event_id " +
                    "WHERE e.location = :location LIMIT 1",
            nativeQuery = true
    )
    List<Object[]> queryFindEventsAndDatesByLocation(@Param("location") String location);

    default EventWithDatesDTO findEventsAndDatesByLocation(String location) {
        List<Object[]> results = this.queryFindEventsAndDatesByLocation(location);

        if (results.isEmpty()) {
            return new EventWithDatesDTO();
        }

        Object[] tuple = results.get(0);
        return Utils.mapObjectArrayToEventWithDatesDTO(tuple);
    }
}
