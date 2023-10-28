package com.usyd.group08.elec5619.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "venue_date")
@IdClass(VenueDate.CompositeKey.class)
public class VenueDate {
    @Id
    @Column(name = "venue_id", nullable = false)
    private int venueId;

    @Id
    @Column(name = "date_slot")
    private Date dateSlot;

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public Date getDateSlot() {
        return dateSlot;
    }

    public void setDateSlot(Date dateSlot) {
        this.dateSlot = dateSlot;
    }

    public static class CompositeKey implements Serializable{
        private int venueId;
        private java.sql.Date dateSlot;
        public CompositeKey(){}
        public CompositeKey(int venueId, Date dateSlot) {
            this.venueId = venueId;
            this.dateSlot = dateSlot;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompositeKey that = (CompositeKey) o;
            return venueId == that.venueId && Objects.equals(dateSlot, that.dateSlot);
        }

        @Override
        public int hashCode() {
            return Objects.hash(venueId, dateSlot);
        }
    }

}
