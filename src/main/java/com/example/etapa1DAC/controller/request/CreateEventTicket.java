package com.example.etapa1DAC.controller.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEventTicket {
    private Long eventId;
    private Integer quantity;
    private String modality;
    private BigDecimal price;
    private String publicRestriction;
    private Integer validDaysLeft;
    private Long eventDateId;
    private List<TicketFieldRequest> fields;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TicketFieldRequest {
        private String name;
        private String type;
        private Boolean required;
    }
}
