package com.example.etapa1DAC.mapper;

import com.example.etapa1DAC.controller.request.BuyTicketRequest;
import com.example.etapa1DAC.domain.PurchaseItem;
import com.example.etapa1DAC.domain.TicketField;
import com.example.etapa1DAC.domain.UserInfoTicket;

public class UserInfoTicketMapper {

    public static UserInfoTicket toEntity(
            BuyTicketRequest.UserInfoRequest userInfoRequest,
            PurchaseItem purchaseItem,
            TicketField ticketField
    ) {
        return UserInfoTicket.builder()
                .purchaseItem(purchaseItem)
                .ticketField(ticketField)
                .infoValue(userInfoRequest.getInfoValue())
                .build();
    }

}
