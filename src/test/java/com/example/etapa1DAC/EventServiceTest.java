package com.example.etapa1DAC;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.etapa1DAC.controller.request.BuyTicketRequest;
import com.example.etapa1DAC.controller.response.BuyTicketResponse;
import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.domain.Ticket;
import com.example.etapa1DAC.domain.TicketType;
import com.example.etapa1DAC.domain.User;
import com.example.etapa1DAC.repository.EventRepository;
import com.example.etapa1DAC.repository.TicketRepository;
import com.example.etapa1DAC.repository.TicketTypeRepository;
import com.example.etapa1DAC.service.AuthenticatedUserService;
import com.example.etapa1DAC.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

public class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private AuthenticatedUserService authenticatedUserService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private TicketTypeRepository ticketTypeRepository;

    @Mock
    private TicketRepository ticketRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuyTicketSuccess() {
        Long eventId = 1L;
        BuyTicketRequest request = new BuyTicketRequest();
        request.setTicketTypeId(1L);
        request.setQuantity(2);
        Map<String, String> requiredInfo = new HashMap<>();
        requiredInfo.put("Name", "Paulo");
        request.setRequiredInfo(requiredInfo);

        User user = new User();
        Event event = new Event();
        TicketType ticketType = new TicketType();
        ticketType.setTotalQuantity(5);
        ticketType.setPrice(50.0);

        when(authenticatedUserService.get()).thenReturn(user);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(ticketTypeRepository.findById(request.getTicketTypeId())).thenReturn(Optional.of(ticketType));

        BuyTicketResponse response = eventService.buyTicket(request, eventId);


        assertNotNull(response);
        assertEquals(100.0, response.getTotalPrice(), 0.01);
        verify(ticketTypeRepository).save(ticketType);
        verify(ticketRepository).save(any(Ticket.class));
    }

    @Test()
    public void testBuyTicketEventNotFound() {
        Long eventId = 1L;
        BuyTicketRequest request = new BuyTicketRequest();
        request.setTicketTypeId(1L);
        request.setQuantity(2);

        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            eventService.buyTicket(request, eventId);
        });
    }
}
