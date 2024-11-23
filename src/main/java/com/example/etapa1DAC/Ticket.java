package com.example.etapa1DAC;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private Restriction restriction;

    @Column
    private String studentId;

    @Column
    private String disabilityId;

    @Column
    private String elderlyId;
}
