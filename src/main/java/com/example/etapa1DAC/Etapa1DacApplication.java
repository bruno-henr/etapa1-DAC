package com.example.etapa1DAC;

import com.example.etapa1DAC.DTO.EventWithDatesDTO;
import com.example.etapa1DAC.domain.*;
import com.example.etapa1DAC.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableAsync
public class Etapa1DacApplication {

	public static void main(String[] args) {
		SpringApplication.run(Etapa1DacApplication.class, args);
	}

}
