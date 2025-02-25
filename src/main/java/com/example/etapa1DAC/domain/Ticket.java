package com.example.etapa1DAC.domain;

import com.example.etapa1DAC.domain.enums.Restriction;
import com.example.etapa1DAC.exceptions.NoTicketsAvailable;
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

    @Column(nullable = false)
    private Integer validDaysLeft;

    @ElementCollection
    @CollectionTable(name = "ticket_required_fields", joinColumns = @JoinColumn(name = "ticket_id"))
    @MapKeyColumn(name = "field_name")
    @Column(name = "field_value")
    private Map<String, String> requiredInfo;

    public Ticket(
            Integer quantity,
            Event event,
            TicketType ticketType,
            User owner,
            int validDaysLeft,
            Map<String, String> requiredInfo
    ) {
        if(!ticketType.hasAvailableTickets(quantity)) {
            throw new NoTicketsAvailable();
        }

        this.quantity = quantity;
        this.event = event;
        this.ticketType = ticketType;
        this.owner = owner;
        this.validDaysLeft = validDaysLeft;
        this.requiredInfo = requiredInfo;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Integer getValidDaysLeft() {
		return validDaysLeft;
	}

	public void setValidDaysLeft(Integer validDaysLeft) {
		this.validDaysLeft = validDaysLeft;
	}

	public Map<String, String> getRequiredInfo() {
		return requiredInfo;
	}

	public void setRequiredInfo(Map<String, String> requiredInfo) {
		this.requiredInfo = requiredInfo;
	}


}
