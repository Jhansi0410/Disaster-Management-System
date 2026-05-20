package com.disaster.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Represents a disaster event (e.g., earthquake, flood).
 * Relationships:
 *   One Disaster → Many Locations
 *   One Disaster → Many Requests
 *   One Disaster → Many Operations
 */
@Entity
@Table(name = "disaster")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"locations", "requests", "operations"})
public class Disaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disaster_id")
    private int disasterId;

    @Column(nullable = false, length = 100)
    private String type; // e.g., Earthquake, Flood, Fire

    @Column(length = 255)
    private String location; // General location description

    @Column(length = 50)
    private String severity; // LOW, MEDIUM, HIGH, CRITICAL

    @Column(length = 500)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_occurred")
    private Date dateOccurred;

    @Column(length = 50)
    private String status; // ACTIVE, RESOLVED, MONITORING

    // One Disaster → Many Locations
    @OneToMany(mappedBy = "disaster", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Location> locations;

    // One Disaster → Many Operations
    @OneToMany(mappedBy = "disaster", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Operation> operations;

    // One Disaster → Many Requests
    @OneToMany(mappedBy = "disaster", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Request> requests;
}
