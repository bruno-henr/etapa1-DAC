package com.example.etapa1DAC.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


import java.util.List;

@Getter
public class BuyTicketRequest {
    @NotEmpty(message = "A lista de itens não pode estar vazia")
    private List<@Valid PurchaseItemRequest> items;


    @Getter
    public static class PurchaseItemRequest {
        @NotNull(message = "O ID do ingresso é obrigatório")
        private Long ticketId;

        @NotNull(message = "A quantidade é obrigatória")
        private Integer quantity;

        @NotEmpty(message = "As informações do usuário não podem estar vazias")
        private List<@Valid UserInfoRequest> userInfos;
    }

    @Getter
    public static class UserInfoRequest {
        @NotNull(message = "O ID do campo do ingresso é obrigatório")
        private Long ticketFieldId;

        @NotBlank(message = "O valor do campo é obrigatório")
        private String infoValue;

    }
}