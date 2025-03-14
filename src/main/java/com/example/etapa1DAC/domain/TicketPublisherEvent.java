package com.example.etapa1DAC.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class TicketPublisherEvent {

    private Long purchaseId;
    private Long userId;
    private BigDecimal totalPrice;
    private List<TicketItem> tickets;
    private Long eventId;



    @Data
    @AllArgsConstructor
    public static class TicketItem {
        private Long ticketId;
        private Integer quantity;

    }
}
