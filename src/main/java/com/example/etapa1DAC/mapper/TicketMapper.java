package com.example.etapa1DAC.mapper;

import com.example.etapa1DAC.controller.request.BuyTicketRequest;
import com.example.etapa1DAC.controller.request.UserSignUpRequest;
import com.example.etapa1DAC.controller.response.BuyTicketResponse;
import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.domain.Ticket;
import com.example.etapa1DAC.domain.TicketType;
import com.example.etapa1DAC.domain.User;

public class TicketMapper {

    public static Ticket toEntity(
            BuyTicketRequest request,
            Event event,
            TicketType ticketType,
            User owner
    ) {
        return Ticket.builder()
                .quantity(request.getQuantity())
                .event(event)
                .ticketType(ticketType)
                .owner(owner)
                .validDaysLeft(request.getValidDaysLeft())
                .requiredInfo(request.getRequiredInfo())
                .build();
    }

    public static BuyTicketResponse toResponse(Ticket ticket, Double totalPrice) {
        return BuyTicketResponse.builder()
                .ticketId(ticket.getId())
                .eventName(ticket.getEvent().getName())
                .ticketType(ticket.getTicketType().getName())
                .quantity(ticket.getQuantity())
                .totalPrice(totalPrice)
                .message("Compra realizada com sucesso!")
                .build();
    }


}
