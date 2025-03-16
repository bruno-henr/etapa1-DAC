package com.example.etapa1DAC.service;

import com.example.etapa1DAC.domain.TicketPublisherEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TicketEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        // Ensure that RabbitTemplate uses JSON serialization
        this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

    public void publishTicketPurchased(TicketPublisherEvent event) {
        rabbitTemplate.convertAndSend("ticketExchange", "generate_ticket", event);
    }
}