package com.example.etapa1DAC.messaging.emailService;

import com.example.etapa1DAC.domain.Ticket;
import com.example.etapa1DAC.domain.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailRequestProducer {
    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendPurchaseConfirmationEmail(User user, Ticket ticket, double totalPrice){
         PurchaseConfirmationEvent event = new PurchaseConfirmationEvent(
                user.getEmail(),
                user.getName(),
                ticket.getEvent().getName(),
                ticket.getTicketType().getName(),
                ticket.getQuantity(),
                totalPrice
        );
        amqpTemplate.convertAndSend(
                "${rabbitmq.email.exchange}",
                "${rabbitmq.email.routing-key-queue}",
                event
        );
    }
}
