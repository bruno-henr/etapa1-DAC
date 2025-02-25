package com.example.etapa1DAC.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class BuyTicketRequest {

    @NotNull(message = "ID do tipo de ticket é obrigatório")
    private Long ticketTypeId;

    @NotNull(message = "Quantidade é obrigatória")
    private Integer quantity;

    @NotNull(message = "Dias de validade são obrigatórios")
    private Integer validDaysLeft;

    @NotNull(message = "Informações requeridas são obrigatórias")
    private Map<String, String> requiredInfo;
}
