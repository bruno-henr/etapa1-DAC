package com.example.etapa1DAC.controller.response;

import com.example.etapa1DAC.domain.enums.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListUserTicketsResponse {
    private Long ticketId;
    private String modality;
    private BigDecimal price;
    private String publicRestriction;
    private Integer quantity;
    private Integer validDaysLeft;
    private Long eventId;
    private String eventName;
    private LocalDateTime eventStartTime;
    private LocalDateTime eventEndTime;
    private PurchaseStatus purchaseStatus;


}
