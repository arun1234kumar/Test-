package com.trimble.carstarter.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;

    @Enumerated(EnumType.STRING)
    private CarStatus status;

    @ManyToOne
    private User owner;

    public enum CarStatus { IDLE, ON_LEASE, ON_SERVICE }

    // Getters and Setters
}
