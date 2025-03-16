package com.example.etapa1DAC.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
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
