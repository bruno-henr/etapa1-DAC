package com.example.etapa1DAC.controller.request;
import com.example.etapa1DAC.domain.EventDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
public class CreateEventRequest {
    @NotNull @NotEmpty(message = "Nome do evento é obrigatório")
    public String name;

    @NotNull @NotEmpty(message = "Descrição do evento é obrigatório")
    public String description;

    private Integer maxCapacity;

    public String location;


    private Set<Long> categoryIds;

    private Set<EventDateRequest> dates;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    public LocalDateTime endTime;
}
