package com.usyd.group08.elec5619.models;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "venue")
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany
    @JoinColumn(name = "venue_id")//stall 那一边的venue id
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Stall> stalls;

    @OneToMany
    @JoinColumn(name = "venue_id")//venue 那一边的venue id
    @OnDelete(action = OnDeleteAction.CASCADE )
    private List<VenueDate> venueDates;


    private String street;
    private String suburb;
    private String state;
    private String description;
    private String picture;
    private double longitude;
    private double latitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<VenueDate> getVenueDates() {
        return venueDates;
    }

    public void setVenueDates(List<VenueDate> venueDates) {
        this.venueDates = venueDates;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public List<Stall> getStalls() {
        return stalls;
    }

    public void setStalls(List<Stall> stalls) {
        this.stalls = stalls;
    }
}
