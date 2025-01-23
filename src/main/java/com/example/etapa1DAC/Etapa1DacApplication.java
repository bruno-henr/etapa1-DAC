package com.example.etapa1DAC;

import com.example.etapa1DAC.DTO.EventWithDatesDTO;
import com.example.etapa1DAC.domain.*;
import com.example.etapa1DAC.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Etapa1DacApplication implements CommandLineRunner {
	@Autowired
	EventRepository eventRepository;

	@Autowired
	EventDateRepository eventDateRepository;

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	TicketTypeRepository ticketTypeRepository;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Etapa1DacApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		US1. Criação de evento
		Event event = new Event(
				"Linkin Park",
				"Show...",
				"Show"
		);
		eventRepository.save(event);

		EventDate eventDate = new EventDate(
			event,
			"SP",
			LocalDateTime.parse("2024-12-22T00:00:00"),
			LocalDateTime.parse("2024-12-22T00:00:00")
		);
		eventDateRepository.save(eventDate);

		Map<String, Object> requiredFields = new HashMap<>();
		requiredFields.put("CPF", Map.of(
				"regex", "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
				"mask", "###.###.###-##",
				"description", "CPF do comprador"
		));
		TicketType ticketType = new TicketType(
				"VIP",
				100,
				2.0d,
				requiredFields
		);
		ticketTypeRepository.save(ticketType);

		User user = new User(
				"Ednaldo Pereira",
				"ednaldo@email.com",
				"123",
				true
		);
		userRepository.save(user);

		Map<String, String> requiredInfo = new HashMap<>();
		requiredInfo.put("CPF", "123.456.789-00");
		requiredInfo.put("MATRICULA", "2022222222");

		Ticket ticket = new Ticket(
				1,
				event,
				ticketType,
				user,
				2,
				requiredInfo
		);
		ticketRepository.save(ticket);
	}
}
