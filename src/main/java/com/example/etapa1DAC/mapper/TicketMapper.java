package com.example.etapa1DAC.mapper;

import com.example.etapa1DAC.controller.request.BuyTicketRequest;
import com.example.etapa1DAC.controller.request.CreateEventTicket;
import com.example.etapa1DAC.controller.response.BuyTicketResponse;
import com.example.etapa1DAC.controller.response.PurchaseItemResponse;
import com.example.etapa1DAC.domain.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TicketMapper {

    public Ticket toTicket(CreateEventTicket request, Event event, EventDate eventDate) {
        Ticket ticket = new Ticket();
        ticket.setQuantity(request.getQuantity());
        ticket.setEvent(event);
        ticket.setEventDate(eventDate);
        ticket.setModality(request.getModality());
        ticket.setPrice(request.getPrice());
        ticket.setPublicRestriction(request.getPublicRestriction());
        ticket.setValidDaysLeft(request.getValidDaysLeft());


        Set<TicketField> fields = request.getFields().stream()
                .map(this::toTicketField)
                .collect(Collectors.toSet());
        ticket.setFields(fields);

        return ticket;
    }

    private TicketField toTicketField(CreateEventTicket.TicketFieldRequest fieldRequest) {
        TicketField ticketField = new TicketField();
        ticketField.setName(fieldRequest.getName());
        ticketField.setType(fieldRequest.getType());
        ticketField.setRequired(fieldRequest.getRequired());
        return ticketField;
    }

    public static BuyTicketResponse toResponse(Purchase purchase, BigDecimal totalPrice) {
        Set<PurchaseItemResponse> items = purchase.getItems().stream()
                .map(item -> new PurchaseItemResponse(
                        item.getTicket().getId(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .collect(Collectors.toSet());

        return BuyTicketResponse.builder()
                .purchaseId(purchase.getId())
                .totalPrice(totalPrice)
                .items(items)
                .build();
    }

}
