package com.disaster.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents a geographic location affected by a disaster.
 * Relationships:
 *   Many Locations → One Disaster
 *   One Location  → Many Operations
 *   One Location  → Many Resources
 */
@Entity
@Table(name = "location")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"disaster", "operations", "resources"})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private int locationId;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 100)
    private String state;

    @Column(length = 100)
    private String country;
    
    private String pincode;
    // Many Locations → One Disaster
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disaster_id", nullable = false)
    private Disaster disaster;

    // One Location → Many Operations
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Operation> operations;

    // One Location → Many Resources
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resource> resources;
}
