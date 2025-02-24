package com.example.etapa1DAC.controller.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class BuyTicketResponse {

    private Long ticketId;
    private String eventName;
    private String ticketType;
    private Integer quantity;
    private Double totalPrice;
    private String message;

}
