package com.example.etapa1DAC;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;

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
    @MapKeyColumn(name = "field_name")
    @Column(name = "field_value", columnDefinition = "TEXT")
    @Convert(converter = JsonConverter.class)
    private Map<String, Object> requiredFields;
    // A ideia de requiredFields é armazenar um Map,
    // sendo a chave o nome do campo requerido e o value com as especificaçes do campo
    // Ex: [ { CPF: { regex: '...', mask: '...', description: '...' } }, { ... } ]

    @Column(nullable = false)
    private Integer totalQuantity;

    @Column(nullable = false)
    private Integer soldQuantity = 0;

    public TicketType(String name, Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
        this.name = name;
    }

    public TicketType(String name, Integer totalQuantity, Map<String, Object> requiredFields) {
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
