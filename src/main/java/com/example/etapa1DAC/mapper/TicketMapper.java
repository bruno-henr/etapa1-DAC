package com.example.etapa1DAC.mapper;

import com.example.etapa1DAC.controller.request.BuyTicketRequest;
import com.example.etapa1DAC.controller.response.BuyTicketResponse;
import com.example.etapa1DAC.domain.*;

import java.math.BigDecimal;

public class TicketMapper {

//    public static Ticket toEntity(
//            BuyTicketRequest request,
//            Event event,
//            User owner
//    ) {
//        return Ticket.builder()
//                .quantity(request.getQuantity())
//                .event(event)
//                .ticketType(ticketType)
//                .owner(owner)
//                .validDaysLeft(request.getValidDaysLeft())
//                .requiredInfo(request.getRequiredInfo())
//                .build();
//    }

    public static BuyTicketResponse toResponse(Purchase purchase, BigDecimal totalPrice) {
        return BuyTicketResponse.builder()
                .purchaseId(purchase.getId())
                .eventName(purchase.getItems().iterator().next().getTicket().getEvent().getName())
                .quantity(purchase.getItems().stream().mapToInt(PurchaseItem::getQuantity).sum())
                .totalPrice(totalPrice.doubleValue())
                .message("Compra realizada com sucesso!")
                .build();
    }


}
