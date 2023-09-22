package com.usyd.group08.elec5619.models;

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
    @Column(name="venue_id")
    private int venueId;
    private Double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public static class CompositeKey implements Serializable {
        private String id;
        private int venueId;

        public CompositeKey(){}
        public CompositeKey(String id, int venueId) {
            this.id = id;
            this.venueId = venueId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompositeKey that = (CompositeKey) o;
            return venueId == that.venueId && Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, venueId);
        }
    }
}


