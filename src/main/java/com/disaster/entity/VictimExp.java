package com.disaster.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "victims")
public class VictimExp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactNumber;
    private String location;
    private String status;

    public VictimExp() {}

    public VictimExp(Long id, String name, String contactNumber, String location, String status) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.location = location;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
