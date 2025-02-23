package com.example.etapa1DAC.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoTicketsAvailable extends RuntimeException {
    public NoTicketsAvailable() {
        super("No tickets available.");
    }
}