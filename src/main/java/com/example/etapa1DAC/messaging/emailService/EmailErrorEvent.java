package com.example.etapa1DAC.messaging.emailService;

import java.io.Serializable;

public record EmailErrorEvent(String emailAddress, String message) implements Serializable {
}
