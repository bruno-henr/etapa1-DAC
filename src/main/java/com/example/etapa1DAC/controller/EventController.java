package com.example.etapa1DAC.controller;

import com.example.etapa1DAC.DTO.CreateEventDTO;
import com.example.etapa1DAC.controller.request.BuyTicketRequest;
import com.example.etapa1DAC.controller.response.BuyTicketResponse;
import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.domain.EventDate;
import com.example.etapa1DAC.service.EventDateService;
import com.example.etapa1DAC.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/event/")
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    EventDateService eventDateService;

    @PostMapping("/create")
    Event createEvent(@RequestBody CreateEventDTO newEvent) {
        return this.eventService.createEvent(newEvent);
    }

    @GetMapping("/list")
    List<Event> listEvents(){return eventService.listEvents();}

    @GetMapping("/filter")
    Page<Event> filterEvents(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) LocalDateTime startTime,
            @RequestParam(required = false) LocalDateTime endTime,
            Pageable pageable){

        return eventService.filterEvents(category, location, startTime, endTime, pageable);
    }

    @PostMapping("{id}/buy")
    BuyTicketResponse buyTicket(@Valid @RequestBody BuyTicketRequest request, @PathVariable Long eventId){
        return eventService.buyTicket(request, eventId);
    }
}
