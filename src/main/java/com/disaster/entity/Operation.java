package com.disaster.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

/*
 * Represents rescue operation details
 */

@Entity
@Table(name = "operation")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@ToString(exclude = {
        "disaster",
        "location",
        "team",
        "victims"
})

public class Operation {

    // =====================================================
    // PRIMARY KEY
    // =====================================================

    @Id

    @GeneratedValue(
            strategy = GenerationType.IDENTITY)

    @Column(name = "operation_id")

    private int operationId;

    // =====================================================
    // OPERATION DETAILS
    // =====================================================

    @Temporal(TemporalType.DATE)

    @Column(name = "start_date")

    private Date startDate;

    

    

    @Column(length = 50)

    private String status;
    // ONGOING / COMPLETED / CANCELLED

    @Column(length = 500)

    private String notes;

    // =====================================================
    // MANY OPERATIONS -> ONE DISASTER
    // =====================================================

    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(
            name = "disaster_id",
            nullable = false)

    private Disaster disaster;

    // =====================================================
    // MANY OPERATIONS -> ONE LOCATION
    // =====================================================

    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(
            name = "location_id",
            nullable = false)

    private Location location;

    // =====================================================
    // MANY OPERATIONS -> ONE TEAM
    // =====================================================

    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(
            name = "team_id",
            nullable = false)

    private RescueTeam team;

    // =====================================================
    // ONE OPERATION -> MANY VICTIMS
    // =====================================================

    @OneToMany(
            mappedBy = "operation",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)

    private List<Victim> victims;
}