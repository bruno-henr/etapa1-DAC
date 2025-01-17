package com.example.etapa1DAC.domain;

import com.example.etapa1DAC.domain.enums.Restriction;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime validUntil;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "ticket_type_id", nullable = false)
    private TicketType ticketType;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ElementCollection
    @CollectionTable(name = "ticket_required_fields", joinColumns = @JoinColumn(name = "ticket_id"))
    @MapKeyColumn(name = "field_name")
    @Column(name = "field_value")
    private Map<String, String> requiredInfo;

    public Ticket(
            LocalDateTime validUntil,
            Integer quantity,
            Event event,
            TicketType ticketType,
            User owner,
            Map<String, String> requiredInfo
    ) {
        this.validUntil = validUntil;
        this.quantity = quantity;
        this.event = event;
        this.ticketType = ticketType;
        this.owner = owner;
        this.requiredInfo = requiredInfo;
    }
}
