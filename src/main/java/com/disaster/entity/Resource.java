package com.disaster.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents a relief resource (food, medicine, equipment, etc.)
 * available at a specific location.
 * Relationships:
 *   Many Resources → One Location
 *   Many Resources ↔ Many RescueTeams (mapped by "resources" in RescueTeam)
 *   Many Resources ↔ Many Victims     (mapped by "resources" in Victim)
 */
@Entity
@Table(name = "resource")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"location", "teams", "victims"})
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private int resourceId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String type; // e.g., FOOD, MEDICINE, EQUIPMENT

    private int quantity;

    @Column(length = 50)
    private String status; // AVAILABLE, DEPLETED, RESERVED

    // Many Resources → One Location
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    // Inverse side of RescueTeam ↔ Resource ManyToMany
    @ManyToMany(mappedBy = "resources")
    private List<RescueTeam> teams;

    // Inverse side of Victim ↔ Resource ManyToMany
    @ManyToMany(mappedBy = "resources")
    private List<Victim> victims;
}
