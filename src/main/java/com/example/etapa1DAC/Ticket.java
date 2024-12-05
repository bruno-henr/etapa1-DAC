package com.example.etapa1DAC;

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

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Event event;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "ticket_type_id", nullable = false)
    private TicketType ticketType;

    @ElementCollection
    @CollectionTable(name = "ticket_required_fields", joinColumns = @JoinColumn(name = "ticket_id"))
    @MapKeyColumn(name = "field_name")
    @Column(name = "field_value")
    private Map<String, String> requiredInfo;

    public Ticket(
            LocalDateTime validUntil,
            Event event,
            Integer quantity,
            TicketType _ticketType,
            Map<String, String> requiredInfo
    ) {
        this.validUntil = validUntil;
        this.event = event;
        this.quantity = quantity;
        this.ticketType = _ticketType;
        this.requiredInfo = requiredInfo;
    }
}
