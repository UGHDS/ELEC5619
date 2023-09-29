package com.usyd.group08.elec5619.repositries;

import com.usyd.group08.elec5619.models.Booking;
import com.usyd.group08.elec5619.models.StallDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends  JpaRepository <Booking,Integer>{
    @Query("SELECT b from Booking b WHERE b.user.id = :userId")
    List<Booking> getBookingByUser(long userId);
}
