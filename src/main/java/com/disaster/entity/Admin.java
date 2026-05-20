package com.disaster.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents a system administrator who manages rescue teams.
 * Relationship: One Admin → Many RescueTeams
 */
@Entity
@Table(name = "admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "teams")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int adminId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(length = 15)
    private String phone;

    // One Admin → Many RescueTeams
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RescueTeam> teams;
}
