package com.disaster.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents a registered user who can raise disaster relief requests.
 * Relationship: One User → Many Requests
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "requests")    // Exclude to avoid circular toString with Request
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(length = 15)
    private String phone;

    @Column(length = 255)
    private String address;

    @Column(length = 50)
    private String role; // e.g., CITIZEN, VOLUNTEER

    // One User → Many Requests (cascade delete requests when user is deleted)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Request> requests;
}
