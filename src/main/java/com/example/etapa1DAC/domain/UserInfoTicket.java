package com.example.etapa1DAC.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class UserInfoTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "purchase_item_id", nullable = false)
    private PurchaseItem purchaseItem;

    @ManyToOne
    @JoinColumn(name = "ticket_field_id", nullable = false)
    private TicketField ticketField;

    @Column(nullable = false)
    private String infoValue;

}
