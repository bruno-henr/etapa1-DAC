package com.example.etapa1DAC.controller;

import com.example.etapa1DAC.DTO.CreateEventDTO;
import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.domain.EventDate;
import com.example.etapa1DAC.service.EventDateService;
import com.example.etapa1DAC.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
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
    List<Event> listEvents() {
        return eventService.listEvents();
    }
}
