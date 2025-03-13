package com.example.etapa1DAC.service;

import com.example.etapa1DAC.domain.PurchaseItem;
import com.example.etapa1DAC.domain.Ticket;
import com.example.etapa1DAC.domain.User;
import com.example.etapa1DAC.exceptions.EmailException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmailService {

    @Autowired JavaMailSender mailSender;

    @Autowired TemplateEngine templateEngine;

    @Async
    public void sendPurchaseConfirmation(User user, List<PurchaseItem> purchaseItems, BigDecimal totalPrice) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(user.getEmail());
            helper.setSubject("Confirmação de Compra - " + purchaseItems.get(0).getTicket().getEvent().getName());

            Context context = new Context();
            context.setVariable("userName", user.getName());
            context.setVariable("event", purchaseItems.get(0).getTicket().getEvent());
            context.setVariable("totalPrice", totalPrice);

            List<Map<String, Object>> ticketsList = purchaseItems.stream().map(purchaseItem -> {
                Map<String, Object> ticketData = new HashMap<>();
                ticketData.put("ticketType", purchaseItem.getTicket().getEvent().getName());
                ticketData.put("quantity", purchaseItem.getQuantity());
                ticketData.put("price", purchaseItem.getPrice());
                return ticketData;
            }).collect(Collectors.toList());

            context.setVariable("tickets", ticketsList);

            String htmlContent = templateEngine.process("email/purchase-confirmation", context);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (Exception e) {
            throw new EmailException("Falha ao enviar e-mail: " + e.getMessage());
        }
    }
}