package com.example.etapa1DAC.mapper;

import com.example.etapa1DAC.domain.Purchase;
import com.example.etapa1DAC.domain.PurchaseItem;
import com.example.etapa1DAC.domain.Ticket;
import com.example.etapa1DAC.domain.User;
import com.example.etapa1DAC.domain.enums.PurchaseStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PurchaseMapper {


    public static Purchase toEntity(User authenticatedUser) {
        return Purchase.builder()
                .user(authenticatedUser)
                .date(LocalDateTime.now())
                .status(PurchaseStatus.PENDING)
                .items(new HashSet<>())
                .build();
    }

    @Getter
    @Setter
    public static class PurchaseWithTicketsResponse {
        private Long id;
        private LocalDateTime date;
        private PurchaseStatus status;
        private Set<TicketDTO> tickets;

        public void PurchaseWithTickets(Purchase purchase) {
            this.id = purchase.getId();
            this.date = purchase.getDate();
            this.status = purchase.getStatus();
            this.tickets = purchase.getItems().stream()
                    .map(PurchaseItem::getTicket)
                    .map(TicketDTO::new)
                    .collect(Collectors.toSet());
        }

        @Getter
        @Setter
        public static class TicketDTO {
            private Long id;
            private String modality;
            private BigDecimal price;
            private Integer quantity;

            public TicketDTO(Ticket ticket) {
                this.id = ticket.getId();
                this.modality = ticket.getModality();
                this.price = ticket.getPrice();
                this.quantity = ticket.getQuantity();
            }
        }


    }
}
