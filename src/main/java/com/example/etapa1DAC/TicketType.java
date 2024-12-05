package com.example.etapa1DAC;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Column(nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(
            name = "ticket_type_required_fields",
            joinColumns = @JoinColumn(name = "ticket_type_id")
    )
    @Column(name = "field_name")
    private List<String> requiredFields;

    @Column(nullable = false)
    private Integer totalQuantity;

    @Column(nullable = false)
    private Integer soldQuantity = 0;

    public TicketType(String name, Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
        this.name = name;
    }

    public TicketType(String name, Integer totalQuantity, List<String> requiredFields) {
        this.totalQuantity = totalQuantity;
        this.name = name;
        this.requiredFields = requiredFields;
    }

    public boolean hasAvailableTickets(Integer quantity) {
        return (this.soldQuantity + quantity) <= this.totalQuantity;
    }

    public void sellTicket(Integer quantity) {
        if (!hasAvailableTickets(quantity)) {
            throw new IllegalStateException(
                    "Sem tickets disponiveis para o tipo: " + this.name
            );
        }
        this.soldQuantity += quantity;
    }
}
