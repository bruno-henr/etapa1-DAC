package com.example.etapa1DAC.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PurchaseItemResponse {
    private Long ticketId;
    private Integer quantity;
    private BigDecimal price;

}
