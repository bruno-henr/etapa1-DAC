package com.example.etapa1DAC.mapper;

import com.example.etapa1DAC.controller.request.BuyTicketRequest;
import com.example.etapa1DAC.domain.Purchase;
import com.example.etapa1DAC.domain.PurchaseItem;
import com.example.etapa1DAC.domain.Ticket;

import java.math.BigDecimal;

public class PurchaseItemMapper {

    public static PurchaseItem toEntity(
            BuyTicketRequest.PurchaseItemRequest itemRequest,
            Purchase purchase,
            Ticket ticket
    ) {
        return PurchaseItem.builder()
                .purchase(purchase)
                .ticket(ticket)
                .quantity(itemRequest.getQuantity())
                .price(calculatePrice(ticket, itemRequest.getQuantity()))
                .build();
    }

    private static BigDecimal calculatePrice(Ticket ticket, Integer quantity) {
        return ticket.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
