package com.example.etapa1DAC.repository;

import com.example.etapa1DAC.domain.EventWithDates;
import com.example.etapa1DAC.Utils;
import com.example.etapa1DAC.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

    @Query(
            value = "SELECT COUNT(*) > 0 " +
                    "FROM event e " +
                    "INNER JOIN event_date ed ON e.id = ed.event_id " +
                    "WHERE ed.location = :location " +
                    "AND ( " +
                    "   (ed.start_time <= :startTime AND ed.end_time >= :startTime) OR " +
                    "   (ed.start_time <= :endTime AND ed.end_time >= :endTime) OR " +
                    "   (ed.start_time >= :startTime AND ed.end_time <= :endTime) " +
                    ")",
            nativeQuery = true
    )
    boolean existsByLocationAndOverlappingTime(
            @Param("location") String location,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}
