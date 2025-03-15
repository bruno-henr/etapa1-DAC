package com.example.etapa1DAC.messaging.emailService;

import com.example.etapa1DAC.domain.PurchaseItem;
import com.example.etapa1DAC.domain.User;
import com.example.etapa1DAC.dto.PurchaseItemEmailDto;
import com.example.etapa1DAC.mapper.PurchaseItemMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmailRequestProducer {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public EmailRequestProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        // Ensure that RabbitTemplate uses JSON serialization
        this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }
    public void sendPurchaseConfirmationEmail(User user, Set<PurchaseItem> purchaseItems, BigDecimal totalPrice){
        Set<PurchaseItemEmailDto> dtoItems = purchaseItems.stream()
                .map(PurchaseItemMapper::toEmailDto)
                .collect(Collectors.toSet());

         PurchaseConfirmationEvent event = new PurchaseConfirmationEvent(
                user.getEmail(),
                user.getName(),
                dtoItems,
                totalPrice
        );
        rabbitTemplate.convertAndSend(
                "ticketExchange",
                "email-notification-request",
                event
        );
    }
}
