package com.disaster.entity;

	import jakarta.persistence.*;

	@Entity
	@Table(name = "requests")
	public class RequestExp {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String type;
	    private String description;
	    private String status;

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;

	    @ManyToOne
	    @JoinColumn(name = "victim_id")
	    private Victim victim;

	    public RequestExp() {}

	    public RequestExp(Long id, String type, String description, String status, User user, Victim victim) {
	        this.id = id;
	        this.type = type;
	        this.description = description;
	        this.status = status;
	        this.user = user;
	        this.victim = victim;
	    }

	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }

	    public String getType() { return type; }
	    public void setType(String type) { this.type = type; }

	    public String getDescription() { return description; }
	    public void setDescription(String description) { this.description = description; }

	    public String getStatus() { return status; }
	    public void setStatus(String status) { this.status = status; }

	    public User getUser() { return user; }
	    public void setUser(User user) { this.user = user; }

	    public Victim getVictim() { return victim; }
	    public void setVictim(Victim victim) { this.victim = victim; }
	}
