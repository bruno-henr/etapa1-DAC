package com.example.etapa1DAC.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class TicketTypeRequest {

    public Long eventId;
    public String name;
    public Integer totalQuantity;
    public Double price;
    public Map<String, Object> requiredFields;

}
