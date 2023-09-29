package com.usyd.group08.elec5619.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stall")
public class Stall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String stallId;
    @Column(name="venue_id")
    private int venueId;
    private Double price;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStallId() {
        return stallId;
    }

    public void setStallId(String stallId) {
        this.stallId = stallId;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}


