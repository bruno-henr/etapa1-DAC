package com.example.etapa1DAC.Event;

import com.example.etapa1DAC.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    public void testEventCreation() {
        String name = "Concert";
        String description = "A live music concert.";
        String category = "Music";

        Event event = new Event(name, description, category);

        assertEquals(name, event.getName());
        assertEquals(description, event.getDescription());
        assertEquals(category, event.getCategory());
        assertNotNull(event.getEventDates());
        assertNotNull(event.getTickets());
    }

    @Test
    public void testAddEventDate() {
        Event event = new Event("Concert", "A live music concert.", "Music");
        EventDate eventDate = new EventDate(event, "Location", LocalDateTime.now(), LocalDateTime.now().plusHours(2));

        event.getEventDates().add(eventDate);

        assertTrue(event.getEventDates().contains(eventDate));
    }

    @Test
    public void testAddTicket() {
        Event event = new Event("Concert", "A live music concert.", "Music");
        Ticket ticket = new Ticket(1, event, new TicketType("", 2, 1.0), new User("", "", "", true), 30, new HashMap<>());

        event.getTickets().add(ticket);

        assertTrue(event.getTickets().contains(ticket));
    }
}
