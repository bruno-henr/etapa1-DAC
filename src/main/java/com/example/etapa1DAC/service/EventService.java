package com.example.etapa1DAC.service;

import com.example.etapa1DAC.controller.request.CreateEventRequest;
import com.example.etapa1DAC.controller.request.BuyTicketRequest;
import com.example.etapa1DAC.controller.request.CreateEventTicket;
import com.example.etapa1DAC.controller.response.BuyTicketResponse;
import com.example.etapa1DAC.domain.*;
import com.example.etapa1DAC.domain.enums.PurchaseStatus;
import com.example.etapa1DAC.exceptions.EventDateConflictException;
import com.example.etapa1DAC.mapper.EventDateMapper;
import com.example.etapa1DAC.mapper.EventMapper;
import com.example.etapa1DAC.mapper.TicketMapper;
import com.example.etapa1DAC.repository.*;
import com.example.etapa1DAC.service.search.FindEvent;
import com.example.etapa1DAC.service.validate.EventDateValidate;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventDateService  eventDateService;
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    AuthenticatedUserService authenticatedUserService;
    @Autowired
    EmailService emailService;
    @Autowired
    EventDateValidate eventDateValidate;
    @Autowired
    FindEvent findEvent;
    @Autowired
    PurchaseItemRepository purchaseItemRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    TicketFieldRepository ticketFieldRepository;
    @Autowired
    UserInfoTicketRepositoy userInfoTicketRepositoy;
    @Autowired
    CategoryEventTypeRepository categoryEventTypeRepository;
    @Autowired
    EventDateRepository eventDateRepository;

    @Transactional
    public Event createEvent(CreateEventRequest newEvent) {
        try {
            User authenticatedUser = authenticatedUserService.get();

            Set<CategoryEventType> categories = newEvent.getCategoryIds().stream()
                    .map(categoryId -> categoryEventTypeRepository.findById(categoryId)
                            .orElseThrow(() -> new RuntimeException("Categoria não encontrada")))
                    .collect(Collectors.toSet());

            Event event = EventMapper.toEntity(newEvent, categories, authenticatedUser);


            if (!eventDateValidate.validEventDate(event.getStartTime(), event.getEndTime(), event.getLocation())) {
                throw new EventDateConflictException();
            }

            event.getDates().forEach(date -> {
                if (!eventDateValidate.validEventDate(date.getStartTime(), date.getEndTime(), date.getLocation())) {
                    throw new EventDateConflictException();
                }
            });

            eventRepository.save(event);

            return event;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "não foi possivel criar o evento" + e.getMessage());
        }
    }

//    public Page<Ticket> myTickets(Pageable pageable){
//        User authenticatedUser = authenticatedUserService.get();
//        return this.ticketRepository.findByOwnerId(authenticatedUser.getId(), pageable);
//    }

    public Ticket createEventTicket(CreateEventTicket request) {

        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        EventDate eventDate = null;
        if (request.getEventDateId() != null) {
            eventDate = eventDateRepository.findById(request.getEventDateId())
                    .orElseThrow(() -> new RuntimeException("Data do evento não encontrada"));
        }


        Ticket ticket = Ticket.builder()
                .event(event)
                .quantity(request.getQuantity())
                .modality(request.getModality())
                .price(request.getPrice())
                .publicRestriction(request.getPublicRestriction())
                .validDaysLeft(request.getValidDaysLeft())
                .eventDate(eventDate)
                .build();

        if (request.getFields() != null) {
            Set<TicketField> fields = request.getFields().stream()
                    .map(fieldRequest -> TicketField.builder()
                            .ticket(ticket)
                            .name(fieldRequest.getName())
                            .type(fieldRequest.getType())
                            .required(fieldRequest.getRequired())
                            .build())
                    .collect(Collectors.toSet());

            ticket.setFields(fields);
        }

        ticketRepository.save(ticket);

        event.getTickets().add(ticket);

        return ticket;
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
                predicates.add(cb.greaterThanOrEqualTo(eventDateJoin.get("startTime"), startTime));
            }

            if(endTime != null) {
                Join<Event, EventDate> eventDateJoin = root.join("eventDates");
                predicates.add(cb.lessThanOrEqualTo(eventDateJoin.get("endTime"), startTime));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Transactional
    public BuyTicketResponse buyTicket(BuyTicketRequest request, Long eventId) {
        User authenticatedUser = authenticatedUserService.get();
        Event event = findEvent.byId(eventId);

        Purchase purchase = Purchase.builder()
                .user(authenticatedUser)
                .date(LocalDateTime.now())
                .status(PurchaseStatus.PENDING)
                .build();
        purchase = purchaseRepository.save(purchase);

        List<PurchaseItem> purchaseItems = new ArrayList<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (PurchaseItem itemRequest : request.getItems()) {
            Ticket ticket = ticketRepository.findById(itemRequest.getTicket().getId())
                    .orElseThrow(() -> new RuntimeException("Ingresso não encontrado"));

            PurchaseItem purchaseItem = PurchaseItem.builder()
                    .purchase(purchase)
                    .ticket(ticket)
                    .quantity(itemRequest.getQuantity())
                    .price(ticket.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())))
                    .build();
            purchaseItem = purchaseItemRepository.save(purchaseItem);
            purchaseItems.add(purchaseItem);

            Set<TicketField> requiredFields = ticket.getFields().stream()
                    .filter(TicketField::getRequired)
                    .collect(Collectors.toSet());

            for (TicketField field : requiredFields) {
                boolean fieldProvided = itemRequest.getUserInfos().stream()
                        .anyMatch(userInfo -> userInfo.getTicketField().getId().equals(field.getId()) && userInfo.getInfoValue() != null);

                if (!fieldProvided) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Campo obrigatório faltando: " + field.getName());
                }
            }

            PurchaseItem finalPurchaseItem = purchaseItem;
            List<UserInfoTicket> userInfoTickets = itemRequest.getUserInfos().stream()
                    .map(userInfoRequest -> {
                        TicketField ticketField = ticketFieldRepository.findById(userInfoRequest.getTicketField().getId())
                                .orElseThrow(() -> new RuntimeException("Campo do ingresso não encontrado"));

                        return UserInfoTicket.builder()
                                .purchaseItem(finalPurchaseItem)
                                .ticketField(ticketField)
                                .infoValue(userInfoRequest.getInfoValue())
                                .build();
                    })
                    .collect(Collectors.toList());

            userInfoTicketRepositoy.saveAll(userInfoTickets);

            ticket.setQuantity(ticket.getQuantity() - itemRequest.getQuantity());
            ticketRepository.save(ticket);

            totalPrice = totalPrice.add(purchaseItem.getPrice());
        }

        emailService.sendPurchaseConfirmation(authenticatedUser, purchaseItems, totalPrice);

        return TicketMapper.toResponse(purchase, totalPrice);
    }
}
