package com.example.etapa1DAC.messaging.emailService;

import com.example.etapa1DAC.dto.PurchaseItemEmailDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public record PurchaseConfirmationEvent(String emailAddress, String userName, Set<PurchaseItemEmailDto> purchaseItems, BigDecimal totalPrice) implements Serializable {
}
