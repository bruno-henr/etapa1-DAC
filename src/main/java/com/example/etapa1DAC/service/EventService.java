package com.example.etapa1DAC.service;

import com.example.etapa1DAC.controller.request.CreateEventRequest;
import com.example.etapa1DAC.controller.request.BuyTicketRequest;
import com.example.etapa1DAC.controller.response.BuyTicketResponse;
import com.example.etapa1DAC.domain.*;
import com.example.etapa1DAC.exceptions.EventDateConflictException;
import com.example.etapa1DAC.mapper.EventDateMapper;
import com.example.etapa1DAC.mapper.EventMapper;
import com.example.etapa1DAC.mapper.TicketMapper;
import com.example.etapa1DAC.repository.EventRepository;
import com.example.etapa1DAC.repository.TicketRepository;
import com.example.etapa1DAC.repository.TicketTypeRepository;
import com.example.etapa1DAC.service.search.FindEvent;
import com.example.etapa1DAC.service.search.FindTicketType;
import com.example.etapa1DAC.service.validate.EventDateValidate;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventDateService  eventDateService;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    TicketTypeRepository ticketTypeRepository;
    @Autowired
    AuthenticatedUserService authenticatedUserService;
    @Autowired
    EmailService emailService;
    @Autowired
    EventDateValidate eventDateValidate;
    @Autowired
    FindEvent findEvent;
    @Autowired
    FindTicketType findTicketType;

    @Transactional
    public Event createEvent(CreateEventRequest newEvent) {
        try {
            Event event = EventMapper.toEntity(newEvent);
            LocalDateTime startTime = LocalDateTime.parse(newEvent.start_time);
            LocalDateTime endTime = LocalDateTime.parse(newEvent.end_time);

            if(!eventDateValidate.validEventDate(startTime, endTime, newEvent.location)) {
                throw new EventDateConflictException();
            }

            if (newEvent.start_time != null && newEvent.end_time != null && newEvent.location != null) {
                EventDate eventDate = EventDateMapper.toEntity(event, newEvent.location, startTime, endTime);

                eventDateService.createEventDate(eventDate);
                eventRepository.save(event);
            }

            return event;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "não foi possivel criar o evento" + e.getMessage());
        }
    }

    public List<Event> listEvents() {
        return this.eventRepository.findAll();
    }

    public Page<Event> filterEvents(String category, String location, LocalDateTime startTime, LocalDateTime endTime, Pageable pageable) {
        Specification<Event> spec = withFilters(category, location, startTime, endTime);
        return this.eventRepository.findAll(spec, pageable);
    }

    private Specification<Event> withFilters(String category, String location, LocalDateTime startTime, LocalDateTime endTime) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (category != null) {
                predicates.add(cb.equal(root.get("category"), category));
            }

            if (location != null) {
                Join<Event, EventDate> eventDateJoin = root.join("eventDates");
                predicates.add(cb.equal(eventDateJoin.get("location"), location));
            }

            if (startTime != null) {
                Join<Event, EventDate> eventDateJoin = root.join("eventDates");
                predicates.add(cb.greaterThanOrEqualTo(eventDateJoin.get("start_time"), startTime));
            }

            if(endTime != null) {
                Join<Event, EventDate> eventDateJoin = root.join("eventDates");
                predicates.add(cb.lessThanOrEqualTo(eventDateJoin.get("end_time"), startTime));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Transactional
    public BuyTicketResponse buyTicket(BuyTicketRequest request, Long eventId) {
        User authenticatedUser = authenticatedUserService.get();
        Event event = findEvent.byId(eventId);
        TicketType ticketType = findTicketType.byId(request.getTicketTypeId());

        Map<String, Object> requiredFields = ticketType.getRequiredFields();
        if (requiredFields != null) {
            requiredFields.keySet().forEach(key -> {
                if (!request.getRequiredInfo().containsKey(key)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campo obrigatório faltando: " + key);
                }
            });
        }

        if (!ticketType.hasAvailableTickets(request.getQuantity())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Ingressos esgotados para o show: " + ticketType.getName());
        }
        ticketType.sellTicket(request.getQuantity());

        Ticket newTicket = TicketMapper.toEntity(request, event, ticketType, authenticatedUser);

        ticketRepository.save(newTicket);

        double totalPrice = ticketType.getPrice() * request.getQuantity();

        emailService.sendPurchaseConfirmation(authenticatedUser, newTicket, totalPrice);

        return TicketMapper.toResponse(newTicket, totalPrice);
    }
}
