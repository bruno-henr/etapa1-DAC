package com.example.etapa1DAC.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserInfoTicketRequest {

    private UUID ticketFieldId;
    private String infoValue;

}
