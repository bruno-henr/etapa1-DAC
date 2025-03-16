package com.example.etapa1DAC.service;

import com.example.etapa1DAC.domain.TicketPublisherEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketEventPublisher {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    Jackson2JsonMessageConverter jsonMessageConverter;

    public void publishTicketPurchased(TicketPublisherEvent event) {
        rabbitTemplate.convertAndSend("ticket.exchange", "ticket.purchased", event);
    }
}