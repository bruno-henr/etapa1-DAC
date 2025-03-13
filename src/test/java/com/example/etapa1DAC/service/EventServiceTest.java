//package com.example.etapa1DAC.service;
//
//import static org.mockito.Mockito.*;
//import static org.assertj.core.api.Assertions.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.*;
//import org.springframework.data.jpa.domain.Specification;
//import java.time.LocalDateTime;
//import java.util.List;
//import com.example.etapa1DAC.service.EventService;
//import com.example.etapa1DAC.repository.EventRepository;
//import com.example.etapa1DAC.domain.Event;
//import com.example.etapa1DAC.domain.EventDate;
//
//@ExtendWith(MockitoExtension.class)
//class EventServiceTest {
//
//    @Mock
//    private EventRepository eventRepository;
//
//    @InjectMocks
//    private EventService eventService;
//
//    private Pageable pageable;
//    private Event event;
//
//    @BeforeEach
//    void setUp() {
//        pageable = PageRequest.of(0, 10);
//        event = new Event(null, null, "Music");
//        EventDate eventDate = new EventDate(event, "New York", LocalDateTime.now().minusDays(1),
//                LocalDateTime.now().plusDays(1));
//        event.getEventDates().add(eventDate);
//    }
//
//    @Test
//    void testFilterEvents_ReturnsFilteredEvents() {
//        LocalDateTime startTime = LocalDateTime.now().minusDays(1);
//        LocalDateTime endTime = LocalDateTime.now().plusDays(1);
//        Page<Event> expectedPage = new PageImpl<>(List.of(event));
//
//        when(eventRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(expectedPage);
//
//        Page<Event> result = eventService.filterEvents("Music", null, startTime, endTime, pageable);
//
//        assertThat(result.getContent()).hasSize(1);
//        assertThat(result.getContent().get(0).getCategory()).isEqualTo("Music");
//        verify(eventRepository).findAll(any(Specification.class), eq(pageable));
//    }
//
//    @Test
//    void testFilterEvents_ReturnsFilteredEventsByLocation() {
//        LocalDateTime startTime = LocalDateTime.now().minusDays(1);
//        LocalDateTime endTime = LocalDateTime.now().plusDays(1);
//
//        Page<Event> expectedPage = new PageImpl<>(List.of(event));
//
//        // Ensure Specification<Event> is properly mocked
//        when(eventRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(expectedPage);
//
//        Page<Event> result = eventService.filterEvents(null, "New York", startTime, endTime, pageable);
//
//        assertThat(result.getContent()).hasSize(1);
//        assertThat(result.getContent().get(0).getEventDates().iterator().next().getLocation()).isEqualTo("New York");
//
//        verify(eventRepository).findAll(any(Specification.class), eq(pageable));
//    }
//}