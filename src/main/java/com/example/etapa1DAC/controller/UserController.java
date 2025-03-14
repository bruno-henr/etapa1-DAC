package com.example.etapa1DAC.controller;

import com.example.etapa1DAC.controller.request.BuyTicketRequest;
import com.example.etapa1DAC.controller.request.UserSignUpRequest;
import com.example.etapa1DAC.controller.response.BuyTicketResponse;
import com.example.etapa1DAC.controller.response.ListUserTicketsResponse;
import com.example.etapa1DAC.controller.response.UserResponse;
import com.example.etapa1DAC.domain.Event;
import com.example.etapa1DAC.domain.Ticket;
import com.example.etapa1DAC.domain.User;
import com.example.etapa1DAC.service.EventService;
import com.example.etapa1DAC.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;


    @PostMapping
    public UserResponse add(@Valid @RequestBody UserSignUpRequest request) {
        return userService.add(request);
    }

    @GetMapping("/me")
    public UserResponse userDetails() {
        return userService.find();
    }

    @GetMapping("/myTickets")
    Page<ListUserTicketsResponse> myTickets(Pageable pageable) {
        return eventService.findUserTickets(pageable);
    }

    @GetMapping("/events/list")
    List<Event> listEvents(){return eventService.listEvents();}

    @GetMapping("/events/filter")
    Page<Event> filterEvents(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) LocalDateTime startTime,
            @RequestParam(required = false) LocalDateTime endTime,
            Pageable pageable){

        return eventService.filterEvents(category, location, startTime, endTime, pageable);
    }

    @PostMapping("events/{eventId}/buy-ticket")
    BuyTicketResponse buyTicket(@Valid @RequestBody BuyTicketRequest request, @PathVariable Long eventId){
        return eventService.buyTicket(request, eventId);
    }
    
}
