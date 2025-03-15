package com.example.etapa1DAC.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class TicketPublisherEvent implements Serializable {

    private Long purchaseId;
    private Long userId;
    private BigDecimal totalPrice;
    private List<TicketItem> tickets;
    private Long eventId;



    @Data
    @AllArgsConstructor
    public static class TicketItem implements  Serializable {
        private Long ticketId;
        private Integer quantity;

    }
}
