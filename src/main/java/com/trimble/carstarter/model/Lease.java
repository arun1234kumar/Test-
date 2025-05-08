package com.trimble.carstarter.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Lease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Car car;

    @ManyToOne
    private User customer;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    // Getters and Setters
}
