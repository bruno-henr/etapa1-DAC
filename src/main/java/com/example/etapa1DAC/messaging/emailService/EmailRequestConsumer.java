package com.example.etapa1DAC.messaging.emailService;

import com.example.etapa1DAC.exceptions.EmailException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailRequestConsumer {

    //@RabbitListener(queues = {"${rabbitmq.email.response-success}"})
    public void processSuccessEmail(EmailSuccessEvent event){

    }

    //@RabbitListener(queues = {"${rabbitmq.email.response-error}"})
    public void processErrorEmail(EmailErrorEvent event){
        throw new EmailException(event.emailAddress()+":"+event.message());
    }
}