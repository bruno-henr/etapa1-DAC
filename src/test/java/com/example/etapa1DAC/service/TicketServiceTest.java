package com.example.etapa1DAC.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.domain.Ticket;
import com.example.etapa1DAC.domain.TicketType;
import com.example.etapa1DAC.domain.User;
import com.example.etapa1DAC.repository.TicketRepository;

import java.util.List;
import java.util.Map;

class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TicketService ticketService;

    private User mockUser;
    private Pageable pageable;
    private Event mockEvent;
    private TicketType mockTicketType;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock user
        mockUser = new User("John Doe", "john@example.com", "password123", true);
        mockUser.setId(1L);

        // Mock event
        mockEvent = new Event("Tech Conference", "A big tech event", "Technology");

        // Mock ticket type with available tickets
        mockTicketType = new TicketType("VIP", 100, 199.99, Map.of("CPF", Map.of("regex", "\\d{11}", "mask", "###.###.###-##")));

        // Pagination settings
        pageable = PageRequest.of(0, 5);
    }

    @Test
    void myTickets_ShouldReturnTickets_WhenUserHasTickets() {
        // Arrange
        Ticket ticket1 = new Ticket(1, mockEvent, mockTicketType, mockUser, 10, Map.of("CPF", "12345678901"));
        Ticket ticket2 = new Ticket(2, mockEvent, mockTicketType, mockUser, 8, Map.of("CPF", "98765432100"));

        Page<Ticket> ticketPage = new PageImpl<>(List.of(ticket1, ticket2), pageable, 2);

        when(userService.getAuthenticatedUser()).thenReturn(mockUser);
        when(ticketRepository.findByOwnerId(mockUser.getId(), pageable)).thenReturn(ticketPage);

        // Act
        Page<Ticket> result = ticketService.myTickets(mockUser.getId(), pageable);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(2, result.getTotalElements());
        assertEquals(1, result.getContent().get(0).getQuantity());
        assertEquals("12345678901", result.getContent().get(0).getRequiredInfo().get("CPF"));
    }

    @Test
    void myTickets_ShouldReturnEmptyPage_WhenUserHasNoTickets() {
        // Arrange
        Page<Ticket> emptyPage = Page.empty(pageable);

        when(userService.getAuthenticatedUser()).thenReturn(mockUser);
        when(ticketRepository.findByOwnerId(mockUser.getId(), pageable)).thenReturn(emptyPage);

        // Act
        Page<Ticket> result = ticketService.myTickets(mockUser.getId(), pageable);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}

