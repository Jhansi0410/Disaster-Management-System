package com.disaster.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * Represents a relief request submitted by a user for a specific disaster.
 * Relationships:
 *   Many Requests → One User
 *   Many Requests → One Disaster
 */
@Entity
@Table(name = "request")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"user", "disaster"})
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private int requestId;

    @Column(name = "request_type", nullable = false, length = 100)
    private String requestType; // e.g., FOOD, MEDICAL, SHELTER

    @Column(length = 50)
    private String status; // PENDING, APPROVED, REJECTED

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "request_date")
    private Date requestDate;

    // Many Requests → One User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Many Requests → One Disaster
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disaster_id", nullable = false)
    private Disaster disaster;
}
