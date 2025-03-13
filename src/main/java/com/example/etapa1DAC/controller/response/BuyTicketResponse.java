package com.example.etapa1DAC.controller.response;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class BuyTicketResponse {

    private UUID purchaseId;
    private Long ticketId;
    private String eventName;
    private String ticketType;
    private Integer quantity;
    private Double totalPrice;
    private String message;

}
