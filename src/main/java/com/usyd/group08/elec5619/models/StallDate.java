package com.usyd.group08.ELEC5619.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "stall_date")
public class StallDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(insertable=false, updatable=false, name="stall_id", referencedColumnName = "id"),
            @JoinColumn(insertable=false, updatable=false, name="venue_id", referencedColumnName = "venue_id")
    })
    private Stall stall;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(insertable=false, updatable=false, name="date_slot", referencedColumnName = "date_slot"),
            @JoinColumn(insertable=false, updatable=false, name="venue_id", referencedColumnName = "venue_id")
    })
    private VenueDate venueDate;
    private String status;
    

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
