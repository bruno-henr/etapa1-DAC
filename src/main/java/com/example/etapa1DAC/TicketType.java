package com.example.etapa1DAC;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "ticket_type")
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false)
    private Restriction type; // Ex: "Idoso", "PCD", "Aluno"

    @Column(nullable = false)
    private Integer totalQuantity;

    @Column(nullable = false)
    private Integer soldQuantity = 0;

    public TicketType(Restriction type, Integer totalQuantity, Event event) {
        this.type = type;
        this.totalQuantity = totalQuantity;
        this.event = event;
    }

    public boolean hasAvailableTickets() {
        return totalQuantity > soldQuantity;
    }

    public void sellTicket() {
        if (!hasAvailableTickets()) {
            throw new IllegalStateException("Sem tickets disponiveis para o tipo: " + type);
        }
        soldQuantity++;
    }
}
