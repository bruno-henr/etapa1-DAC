package com.example.etapa1DAC.messaging.emailService;

import com.example.etapa1DAC.domain.PurchaseItem;
import com.example.etapa1DAC.domain.Ticket;
import com.example.etapa1DAC.domain.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class EmailRequestProducer {
    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendPurchaseConfirmationEmail(User user, Set<PurchaseItem> purchaseItems, BigDecimal totalPrice){
         PurchaseConfirmationEvent event = new PurchaseConfirmationEvent(
                user.getEmail(),
                user.getName(),
                totalPrice
        );
        amqpTemplate.convertAndSend(
                "ticketExchange",
                "email-notification-request",
                "test"
        );
    }
}
