package com.example.etapa1DAC.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class TicketTypeDTO {
    public Long eventId;
    public String name;
    public Integer totalQuantity;
    public Double price;
    public Map<String, Object> requiredFields;
}
