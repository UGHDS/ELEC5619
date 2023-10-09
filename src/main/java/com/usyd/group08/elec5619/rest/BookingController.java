package com.usyd.group08.elec5619.rest;


import com.usyd.group08.elec5619.aop.ValidateUserType;
import com.usyd.group08.elec5619.models.Booking;
import com.usyd.group08.elec5619.models.StallDate;
import com.usyd.group08.elec5619.models.User;
import com.usyd.group08.elec5619.repositries.BookingRepository;
import com.usyd.group08.elec5619.repositries.PaymentRepository;
import com.usyd.group08.elec5619.repositries.StallDateRepository;
import com.usyd.group08.elec5619.repositries.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    StallDateRepository stallDateRepository;

    @Autowired
    HttpSession httpSession;

    @PostMapping
    @ValidateUserType
    @Operation(summary = "User booking", description = "Pass stallDateId")
    public boolean booking(@RequestParam int stallDateId) {
        User user = (User) httpSession.getAttribute("currentUser");
        Optional<StallDate> stallDateOptional = stallDateRepository.findById(stallDateId);
        if (stallDateOptional.isPresent() && stallDateOptional.get().getStatus().equals("Available")) {
            StallDate stallDate = stallDateOptional.get();
            Booking booking = new Booking();
            booking.setStallDate(stallDate);
            booking.setUser(user);
            //获取当前现实生活中的时间
            booking.setBookingTime(Timestamp.valueOf(LocalDateTime.now()));
            booking.setStatus("Booked");
            bookingRepository.save(booking);
            stallDate.setStatus("Booked");
            stallDateRepository.save(stallDate);
            return true;
        }
        return false;
    }

    @DeleteMapping
    @ValidateUserType
    @Operation(summary = "Cancel booking", description = "pass booking id")
    public boolean cancelBooking(@RequestParam int bookingId) {
        User user = (User) httpSession.getAttribute("currentUser");
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (bookingOptional.isPresent() && user.getType().equals("admin") || bookingOptional.get().getUser().getId().equals(user.getId())) {
            Booking booking = bookingOptional.get();
            booking.setStatus("Cancelled");
            bookingRepository.save(booking);
            return true;
        }
        return false;
    }

    @PostMapping("/user/{userId}")
    @ValidateUserType(type = "admin")
    @Operation(summary = "Find all booking history by user Id (admin only)", description = "pass user id will return his booking history")
    public List<Booking> bookingHistory(@PathVariable String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return bookingRepository.getBookingByUser(user.get().getId());
        }
        return null;
    }

    @PostMapping("/own")
    @ValidateUserType
    @Operation(summary = "Find all booking history from current user", description = "user check his booking history")
    public List<Booking> ownBookingHistory() {
        User user = (User) httpSession.getAttribute("currentUser");
        return bookingRepository.getBookingByUser(user.getId());
    }

    //payment的账单查询
    @PostMapping("/{bookingId}")
    @ValidateUserType
    @Operation(summary = "Get booking details by bookingId from current user", description = "pass bookingId will return booking details")
    public Booking bookingDetails(@PathVariable int bookingId) {
        User user = (User) httpSession.getAttribute("currentUser");
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (bookingOptional.isPresent() && user.getType().equals("admin") || bookingOptional.get().getUser().getId().equals(user.getId())) {
            return bookingOptional.get();
        }
        return null;
    }


}
