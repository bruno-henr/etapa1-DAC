package com.example.etapa1DAC.controller.request;
import com.example.etapa1DAC.domain.EventDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class CreateEventRequest {
    @NotBlank(message = "Nome do evento é obrigatório")
    public String name;

    @NotBlank(message = "Descrição do evento é obrigatório")
    public String description;

    @NotNull(message = "Capacide máxima é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private Integer maxCapacity;

    @NotBlank(message = "local do evento é obrigatória")
    public String location;

    @NotEmpty(message = "evento precisa possuir no minimo uma categoria")
    private Set<Long> categoryIds;

    @NotEmpty(message = "datas do evento são obrigatorias")
    private Set<EventDateRequest> dates;

    @NotNull(message = "data de inicio é obrigatoria")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime startTime;

    @NotNull(message = "data de encerramento é obrigatória")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime endTime;

    @Getter
    public static class EventDateRequest {

        @NotNull(message = "data de inicio é obrigatória para todos os dias de show")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime startTime;

        @NotNull(message = "data de encerramento é obrigatória para todos os dias de show")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime endTime;

        @NotBlank(message = "localização do ingresso é obrigatória")
        private String location;
    }
}
