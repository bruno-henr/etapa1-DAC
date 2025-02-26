package com.example.etapa1DAC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class Etapa1DacApplication {

	public static void main(String[] args) {
		SpringApplication.run(Etapa1DacApplication.class, args);
	}

}
