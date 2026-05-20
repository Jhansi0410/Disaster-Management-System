package com.disaster.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents a disaster victim rescued or assisted during an operation.
 * Relationships:
 *   Many Victims → One Operation
 *   Many Victims ↔ Many Resources (join table: victim_resource)
 */
@Entity
@Table(name = "victim")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"operation", "resources"})
public class Victim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "victim_id")
    private int victimId;

    @Column(nullable = false, length = 100)
    private String name;

    private int age;

    @Column(length = 10)
    private String gender;

    @Column(name = "injury_details", length = 500)
    private String injuryDetails;

    @Column(length = 50)
    private String status; // RESCUED, HOSPITALIZED, DECEASED, MISSING

    // Many Victims → One Operation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_id", nullable = false)
    private Operation operation;

    // Many Victims ↔ Many Resources
    @ManyToMany
    @JoinTable(
        name = "victim_resource",
        joinColumns        = @JoinColumn(name = "victim_id"),
        inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private List<Resource> resources;
}
