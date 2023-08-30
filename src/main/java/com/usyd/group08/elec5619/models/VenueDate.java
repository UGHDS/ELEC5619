package com.usyd.group08.ELEC5619.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "venue_date")
@IdClass(VenueDate.CompositeKey.class)
public class VenueDate {
    @Id
    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @Id
    @Column(name = "date_slot")
    private java.sql.Date dateSlot;

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Date getDateSlot() {
        return dateSlot;
    }

    public void setDateSlot(Date dateSlot) {
        this.dateSlot = dateSlot;
    }

    public static class CompositeKey implements Serializable{
        private Venue venue;
        private java.sql.Date dateSlot;

        public CompositeKey(){}
        public CompositeKey(Venue venue, Date dateSlot) {
            this.venue = venue;
            this.dateSlot = dateSlot;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompositeKey that = (CompositeKey) o;
            return Objects.equals(venue, that.venue) && Objects.equals(dateSlot, that.dateSlot);
        }

        @Override
        public int hashCode() {
            return Objects.hash(venue, dateSlot);
        }
    }

}
