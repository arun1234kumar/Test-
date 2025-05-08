package com.trimble.carstarter.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role { ADMIN, OWNER, CUSTOMER }

    // Getters and Setters
}
