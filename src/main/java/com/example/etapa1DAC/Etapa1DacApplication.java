package com.example.etapa1DAC;

import com.example.etapa1DAC.repositories.EventDateRepository;
import com.example.etapa1DAC.repositories.EventRepository;
import com.example.etapa1DAC.repositories.TicketRepository;
import com.example.etapa1DAC.repositories.TicketTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	public static void main(String[] args) {
		SpringApplication.run(Etapa1DacApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// US1. Criação de evento
//		Event event = new Event(
//				"Linkin Park",
//				"Show...",
//				"SP",
//				2,
//				50.0
//		);
//
//		eventRepository.save(event);
//		EventWithDatesDTO eventFind = eventRepository.findEventsAndDatesByLocation("SP");
//		System.out.println("Evento encontrado => "+eventFind);
//		LocalDateTime now = LocalDateTime.now();
//
//		EventDate eventDate = new EventDate(
//			event,
//			LocalDateTime.parse("2024-12-22T00:00:00"),
//			LocalDateTime.parse("2024-12-22T00:00:00")
//		);
//		System.out.println("Evento salvo: " + event);
		System.out.println("");
//		TicketMode t1 = new TicketMode("Estudante", List.of("MATRICULA", "CPF"));
//		ticketModeRepository.save(t1);

//		Event event = eventRepository.findById(1L).orElseThrow(() -> new RuntimeException("Event not found"));;
//		TicketMode ticketMode = ticketModeRepository.findById(1L).orElseThrow(() -> new RuntimeException("TicketMode not found"));;
//		Map<String, String> requiredInfo = new HashMap<>();
//		requiredInfo.put("CPF", "123.456.789-00");
//		requiredInfo.put("MATRICULA", "2022222222");

//		Ticket ticket = new Ticket(
//				LocalDateTime.of(2024, 12, 1, 23, 59),
//				event,
//				1,
//				ticketMode,
//				requiredInfo
//		);
//
//		ticketRepository.save(ticket);
		Map<String, Object> requiredFields = new HashMap<>();
		requiredFields.put("CPF", Map.of(
				"regex", "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
				"mask", "###.###.###-##",
				"description", "CPF do comprador"
		));

		TicketType ticketType = new TicketType(
				"VIP",
				100,
				requiredFields
		);

		ticketTypeRepository.save(ticketType);
	}
}
