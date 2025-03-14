package com.example.etapa1DAC.controller;

import com.example.etapa1DAC.controller.request.CreateEventRequest;
import com.example.etapa1DAC.controller.request.CreateEventTicket;
import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.domain.Ticket;
import com.example.etapa1DAC.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EventService eventService;

    @PostMapping("/event/create")
    Event createEvent(@Valid @RequestBody CreateEventRequest newEvent) {
        return this.eventService.createEvent(newEvent);
    }

    @PostMapping("/event/createTicket")
    Ticket createEventTicket(@Valid @RequestBody CreateEventTicket newTicket) {
        return this.eventService.createEventTicket(newTicket);
    }



}
