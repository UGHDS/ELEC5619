package com.usyd.group08.elec5619.models;

import jakarta.persistence.*;

@Entity
@Table(name = "stall_date")
public class StallDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="stall_id")
    private Stall stall;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="date_slot", referencedColumnName = "date_slot"),
            @JoinColumn(name="venue_id", referencedColumnName = "venue_id")
    })
    private VenueDate venueDate;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stall getStall() {
        return stall;
    }

    public void setStall(Stall stall) {
        this.stall = stall;
    }

    public VenueDate getVenueDate() {
        return venueDate;
    }

    public void setVenueDate(VenueDate venueDate) {
        this.venueDate = venueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
