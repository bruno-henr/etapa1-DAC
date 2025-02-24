package com.example.etapa1DAC.DTO;

import java.util.Map;

public class TicketTypeDTO {
    public String name;
    public Integer totalQuantity;
    public Double price;
    public Map<String, Object> requiredFields;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
