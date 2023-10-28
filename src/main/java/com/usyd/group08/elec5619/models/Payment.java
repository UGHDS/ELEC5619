package com.usyd.group08.elec5619.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_id")
    private int bookingId;

    private String paymentItem;

    private double amount;

    private Timestamp paymentDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getPaymentItem() {
        return paymentItem;
    }

    public void setPaymentItem(String paymentItem) {
        this.paymentItem = paymentItem;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }
}
