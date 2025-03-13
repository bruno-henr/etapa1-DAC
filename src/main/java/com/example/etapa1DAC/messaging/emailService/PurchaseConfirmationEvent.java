package com.example.etapa1DAC.messaging.emailService;

import java.io.Serializable;

public record PurchaseConfirmationEvent(String emailAddress, String userName, String eventName, String ticketType, Integer quantity, double totalPrice) implements Serializable {
}
