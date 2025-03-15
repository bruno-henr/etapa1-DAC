package com.example.etapa1DAC.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEvent {
    @EmbeddedId
    private EventCategoryId id;

    @ManyToOne

    @MapsId("eventId")
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private CategoryEventType category;
}

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class EventCategoryId implements Serializable {
    private UUID eventId;
    private Long categoryId;
}

