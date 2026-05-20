package com.disaster.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents a rescue team deployed during disaster operations.
 * Relationships:
 *   Many RescueTeams → One Admin
 *   One RescueTeam  → Many Operations
 *   Many RescueTeams ↔ Many Resources (join table: team_resource)
 */
@Entity
@Table(name = "rescue_team")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"admin", "operations", "resources"})
public class RescueTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int teamId;

    @Column(name = "team_name", nullable = false, length = 150)
    private String teamName;

    @Column(length = 300)
    private String description;

    @Column(name = "contact_no", length = 15)
    private String contactNo;

    @Column(length = 50)
    private String status; // AVAILABLE, BUSY, STANDBY

    // Many RescueTeams → One Admin
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    // One RescueTeam → Many Operations
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Operation> operations;

    // Many RescueTeams ↔ Many Resources
    @ManyToMany
    @JoinTable(
        name = "team_resource",
        joinColumns        = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private List<Resource> resources;
}
