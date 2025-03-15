package com.example.etapa1DAC.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class CreateEventTicket {

    @NotNull(message = "O ID do evento é obrigatório")
    @Positive(message = "O ID do evento deve ser um número positivo")
    private Long eventId;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private Integer quantity;

    @NotBlank(message = "A modalidade é obrigatória")
    private String modality;

    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    private BigDecimal price;

    @NotBlank(message = "A restrição pública é obrigatória")
    private String publicRestriction;

    @NotNull(message = "Dias válidos restantes é obrigatório")
    @Min(value = 1, message = "Dias válidos deve ser no mínimo 1")
    private Integer validDaysLeft;

    @NotNull(message = "O ID da data do evento é obrigatório")
    @Positive(message = "O ID da data do evento deve ser positivo")
    private Long eventDateId;

    @NotEmpty(message = "A lista de campos não pode estar vazia")
    @Valid
    private List<TicketFieldRequest> fields;

    @Getter
    public static class TicketFieldRequest {

        @NotBlank(message = "O nome do campo é obrigatório")
        private String name;

        @NotBlank(message = "O tipo do campo é obrigatório")
        private String type;

        @NotNull(message = "O indicador de obrigatoriedade é requerido")
        private Boolean required;
    }
}
