package com.example.etapa1DAC.DTO;
import jakarta.annotation.Nullable;

public class CreateEventDTO {
    @Nullable
    public String name;
    @Nullable
    public String description;
    @Nullable
    public String category;
    public String location;
    public String start_time;
    public String end_time;
}
