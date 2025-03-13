package com.example.etapa1DAC.messaging.emailService;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailAmqpConfig {
    @Bean
    public Queue requestQueue() {
        return new Queue("${rabbitmq.email.queue}");
    }

    @Bean
    public Queue responseSuccessQueue() {
        return new Queue("${rabbitmq.email.response-success}");
    }

    @Bean
    public Queue responseErrorQueue() {
        return new Queue("${rabbitmq.email.response-error}");
    }

    @Bean
    public TopicExchange requestExchange() {
        return new TopicExchange("${rabbitmq.email.exchange}");
    }

    @Bean
    public Binding requestBinding(Queue requestQueue, TopicExchange requestExchange) {
        return BindingBuilder.bind(requestQueue).to(requestExchange).with("${rabbitmq.email.routing-key.queue}");
    }

    @Bean
    public Binding responseSuccessBinding(Queue responseSuccessQueue, TopicExchange requestExchange) {
        return BindingBuilder.bind(responseSuccessQueue).to(requestExchange).with("${rabbitmq.email.routing-key.response-success}");
    }

    @Bean
    public Binding responseErrorBinding(Queue responseErrorQueue, TopicExchange requestExchange) {
        return BindingBuilder.bind(responseErrorQueue).to(requestExchange).with("${rabbitmq.email.routing-key.response-error}");
    }
}
