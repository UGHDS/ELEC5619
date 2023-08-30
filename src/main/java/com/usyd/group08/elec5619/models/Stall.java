package com.usyd.group08.ELEC5619.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "stall")
@IdClass(Stall.CompositeKey.class)
public class Stall {

    @Id
    private String id;

    @Id
    @ManyToOne
    @JoinColumn(name ="venue_id", nullable = false)
    private Venue venue;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public static class CompositeKey implements Serializable {
        private String id;
        private Venue venue;

        public CompositeKey(){}
        public CompositeKey(String id, Venue venue) {
            this.id = id;
            this.venue = venue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompositeKey that = (CompositeKey) o;
            return Objects.equals(id, that.id) && Objects.equals(venue, that.venue);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, venue);
        }
    }
}


