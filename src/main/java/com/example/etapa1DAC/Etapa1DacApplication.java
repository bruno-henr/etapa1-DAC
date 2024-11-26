package com.example.etapa1DAC;

import com.example.etapa1DAC.DTO.EventWithDatesDTO;
import com.example.etapa1DAC.repositories.EventDateRepository;
import com.example.etapa1DAC.repositories.EventRepository;
import com.example.etapa1DAC.repositories.TicketRepository;
import com.example.etapa1DAC.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Etapa1DacApplication implements CommandLineRunner {
	@Autowired
	EventRepository eventRepository;

	@Autowired
	EventDateRepository eventDateRepository;

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	EventService eventService;

	public static void main(String[] args) {
		SpringApplication.run(Etapa1DacApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// US1. Criação de evento
		Event event = new Event(
				"Linkin Park",
				"Show...",
				"SP",
				2,
				50.0
		);
		//	eventRepository.save(event);
		EventDate eventDate = new EventDate(
			event,
			LocalDateTime.parse("2024-12-22T00:00:00"),
			LocalDateTime.parse("2024-12-23T00:00:00")
		);

		// US2. Validação de Conflitos de Horário e Local
		if(!eventService.validEventDate(eventDate.getStart_time(), eventDate.getEnd_time(), "SP")) {
			System.out.println("Conflito entre de datas com um evento já existente.");
		}

		System.out.println("Evento salvo: " + event);
		System.out.println("");
	}
}
