package com.example.etapa1DAC.config;

import jakarta.validation.Valid;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "ticketExchange";
    public static final String QUEUE_TICKET_GENERATION = "ticketGenerationQueue";
    public static final String QUEUE_EMAIL_NOTIFICATION = "email-notification-request-queue";

    @Bean
    public Queue ticketGenerationQueue() {
        return new Queue(QUEUE_TICKET_GENERATION, true);
    }

    @Bean
    public Queue emailNotificationQueue() {
        return new Queue(QUEUE_EMAIL_NOTIFICATION);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingTicketGeneration(Queue ticketGenerationQueue, DirectExchange exchange) {
        return BindingBuilder.bind(ticketGenerationQueue).to(exchange).with("generate_ticket");
    }

    @Bean
    public Binding bindingEmailNotification(Queue emailNotificationQueue, DirectExchange exchange) {
        return BindingBuilder.bind(emailNotificationQueue).to(exchange).with("email-notification-request");
    }
}