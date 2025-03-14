package com.example.etapa1DAC.controller.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Builder
@Setter
public class BuyTicketResponse {
    private Long purchaseId;
    private BigDecimal totalPrice;
    private Set<PurchaseItemResponse> items;

}

