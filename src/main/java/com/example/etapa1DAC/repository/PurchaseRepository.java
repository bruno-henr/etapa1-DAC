package com.example.etapa1DAC.repository;

import com.example.etapa1DAC.controller.response.ListUserTicketsResponse;
import com.example.etapa1DAC.domain.Purchase;
import com.example.etapa1DAC.domain.Ticket;
import com.example.etapa1DAC.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    @Query("SELECT new com.example.etapa1DAC.controller.response.ListUserTicketsResponse(" +
            "t.id, t.modality, t.price, t.publicRestriction, pi.quantity, t.validDaysLeft, " +
            "e.id, e.name, e.startTime, e.endTime, p.status) " +
            "FROM Purchase p " +
            "JOIN p.items pi " +
            "JOIN pi.ticket t " +
            "JOIN t.event e " +
            "WHERE p.user.id = :userId " +
            "ORDER BY p.date DESC")
    Page<ListUserTicketsResponse> findTicketsAndEventsByUser(@Param("userId") Long userId, Pageable pageable);
}
