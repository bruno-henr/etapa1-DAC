package com.example.etapa1DAC.controller.request;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateEventRequest {
    @NotNull @NotEmpty(message = "Nome do evento é obrigatório")
    public String name;

    @NotNull @NotEmpty(message = "Descrição do evento é obrigatório")
    public String description;

    @NotNull @NotEmpty(message = "Categoria do evento é obrigatório")
    public String category;

    public String location;
    public String start_time;
    public String end_time;
}
