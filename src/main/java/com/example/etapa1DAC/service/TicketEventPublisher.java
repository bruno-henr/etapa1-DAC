package com.example.etapa1DAC.service;

import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.domain.TicketPublisherEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class TicketEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public TicketEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishTicketPurchased(TicketPublisherEvent event) {
        rabbitTemplate.convertAndSend("ticket.exchange", "ticket.purchased", event);
    }
}