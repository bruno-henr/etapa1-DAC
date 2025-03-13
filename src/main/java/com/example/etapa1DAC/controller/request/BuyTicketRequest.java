package com.example.etapa1DAC.controller.request;

import com.example.etapa1DAC.domain.PurchaseItem;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
public class BuyTicketRequest {

    private List<PurchaseItem> items;
}
