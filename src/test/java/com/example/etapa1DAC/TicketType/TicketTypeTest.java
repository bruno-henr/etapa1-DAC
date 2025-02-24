package com.example.etapa1DAC.TicketType;

import com.example.etapa1DAC.domain.TicketType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TicketTypeTest {
    @Test
    public void testTicketTypeCreation() {
        String name = "VIP";
        Integer totalQuantity = 100;
        Double price = 200.00;
        Map<String, Object> requiredFields = new HashMap<>();
        requiredFields.put("CPF", Map.of("regex", "\\d{11}", "mask", "###.###.###-##", "description", "Brazilian CPF"));

        TicketType ticketType = new TicketType(name, totalQuantity, price, requiredFields);

        assertEquals(name, ticketType.getName());
        assertEquals(totalQuantity, ticketType.getTotalQuantity());
        assertEquals(price, ticketType.getPrice());
        assertEquals(requiredFields, ticketType.getRequiredFields());
    }

    @Test
    public void testHasAvailableTickets() {
        TicketType ticketType = new TicketType("VIP", 100, 200.00);

        assertTrue(ticketType.hasAvailableTickets(50));
        assertFalse(ticketType.hasAvailableTickets(150));
    }

    @Test
    public void testSellTicket() {
        TicketType ticketType = new TicketType("VIP", 100, 200.00);

        ticketType.sellTicket(50);
        assertEquals(50, ticketType.getSoldQuantity());

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            ticketType.sellTicket(60);
        });
        assertEquals("Sem tickets disponiveis para o tipo: VIP", exception.getMessage());
    }
}
