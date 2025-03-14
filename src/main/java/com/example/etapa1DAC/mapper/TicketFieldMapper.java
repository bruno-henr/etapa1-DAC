package com.example.etapa1DAC.mapper;

import com.example.etapa1DAC.controller.request.CreateEventTicket;
import com.example.etapa1DAC.domain.Ticket;
import com.example.etapa1DAC.domain.TicketField;

import java.util.Set;
import java.util.stream.Collectors;

public class TicketFieldMapper {

    public static Set<TicketField> toEntity(CreateEventTicket request, Ticket ticket) {
        return request.getFields().stream()
                .map(fieldRequest -> toTicketField(fieldRequest, ticket))
                .collect(Collectors.toSet());
    }

    private static TicketField toTicketField(CreateEventTicket.TicketFieldRequest fieldRequest, Ticket ticket) {
        return TicketField.builder()
                .ticket(ticket)
                .name(fieldRequest.getName())
                .type(fieldRequest.getType())
                .required(fieldRequest.getRequired())
                .build();
    }

}
