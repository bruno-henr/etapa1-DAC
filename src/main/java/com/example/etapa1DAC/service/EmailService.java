package com.example.etapa1DAC.service;

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

@Service
public class EmailService {

    @Autowired JavaMailSender mailSender;

    @Autowired TemplateEngine templateEngine;

    @Async
    public void sendPurchaseConfirmation(User user, Ticket ticket, double totalPrice) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(user.getEmail());
            helper.setSubject("Confirmação de Compra - " + ticket.getEvent().getName());

            Context context = new Context();
            context.setVariable("userName", user.getName());
            context.setVariable("event", ticket.getEvent());
            context.setVariable("ticketType", ticket.getTicketType().getName());
            context.setVariable("quantity", ticket.getQuantity());
            context.setVariable("totalPrice", totalPrice);

            String htmlContent = templateEngine.process("email/purchase-confirmation", context);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (Exception e) {
            throw new EmailException("Falha ao enviar e-mail: " + e.getMessage());
        }
    }
}