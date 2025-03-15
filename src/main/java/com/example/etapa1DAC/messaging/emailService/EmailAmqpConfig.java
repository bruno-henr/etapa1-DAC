package com.example.etapa1DAC.messaging.emailService;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailAmqpConfig {
    @Bean
    public Queue requestQueue() {
        return new Queue("${spring.rabbitmq.queues.email-notification-request-queue}");
    }

    @Bean
    public Binding requestBinding(Queue requestQueue, DirectExchange requestExchange) {
        return BindingBuilder.bind(requestQueue).to(requestExchange).with("${spring.rabbitmq.routing-keys.email-notification-request-queue}");
    }
}
