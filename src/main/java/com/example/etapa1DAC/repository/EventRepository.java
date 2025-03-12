package com.example.etapa1DAC.repository;

import com.example.etapa1DAC.domain.EventWithDates;
import com.example.etapa1DAC.Utils;
import com.example.etapa1DAC.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    @Query(
            value = "SELECT e.id AS eventId, " +
                    "e.name AS eventName, " +
                    "e.description AS eventDescription, " +
                    "ed.location AS eventLocation, " +
                    "ed.id AS eventDateId, " +
                    "ed.start_time AS startTime, " +
                    "ed.end_time AS endTime " +
                    "FROM event e " +
                    "INNER JOIN event_date ed ON e.id = ed.event_id " +
                    "WHERE ed.location = :location LIMIT 1",
            nativeQuery = true
    )
    List<Object[]> queryFindEventsAndDatesByLocation(@Param("location") String location);

    default EventWithDates findEventsAndDatesByLocation(String location) {
        List<Object[]> results = this.queryFindEventsAndDatesByLocation(location);

        if (results.isEmpty()) {
            return new EventWithDates();
        }

        Object[] tuple = results.get(0);
        return Utils.mapObjectArrayToEventWithDatesDTO(tuple);
    }
}
