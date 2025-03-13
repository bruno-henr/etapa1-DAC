package com.example.etapa1DAC.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Data
public class PurchaseItemRequest {

    @NotNull(message = "ID do tipo de ticket é obrigatório")
    private Long ticketId;

    @NotNull(message = "Quantidade é obrigatória")
    private Integer quantity;

    @NotNull(message = "Informações requeridas são obrigatórias")
    private Map<String, String> requiredInfo;



}
