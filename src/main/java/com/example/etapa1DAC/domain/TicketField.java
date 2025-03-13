package com.example.etapa1DAC.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class TicketField {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @OneToMany(mappedBy = "ticketField", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserInfoTicket> userInfos;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Boolean required;

}
