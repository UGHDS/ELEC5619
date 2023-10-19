package com.usyd.group08.elec5619.rest;

import com.usyd.group08.elec5619.aop.ValidateUserType;
import com.usyd.group08.elec5619.models.*;
import com.usyd.group08.elec5619.repositries.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    VenueRepository venueRepository;

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

    @GetMapping("/user/allBookingHistory")
    @ValidateUserType(type = "admin")
    @Operation(summary = "Find all booking history", description = "get all booking history")
    public List<List<Map<String, Object>>> bookingHistory() {
        List<Booking> bookingList = bookingRepository.findAll();
        List<List<Map<String, Object>>> responses = new ArrayList<>();
        List<Map<String, Object>> responseBooked = new ArrayList<>();
        List<Map<String, Object>> responseElse = new ArrayList<>();

        for (Booking booking: bookingList) {
            Map<String, Object> response = new HashMap<>();
            int bookingId = booking.getId();
            String stallId = booking.getStallDate().getStall().getStallId();
            int venueId = booking.getStallDate().getVenueDate().getVenueId();
            Venue venue = venueRepository.findById(venueId).get();

            String venueName = venue.getVenueName();
            String street = venue.getStreet();
            String suburb = venue.getSuburb();
            String state = venue.getState();
            String address = street+", "+suburb+" "+state;

            Date dateSlot= booking.getStallDate().getVenueDate().getDateSlot();
            String status = booking.getStatus();
            double totalPrice = booking.getStallDate().getStall().getPrice();

            List<Payment> paymentList= booking.getPayments();
            for (Payment payment: paymentList) {
                double itemPrice = payment.getAmount();
                totalPrice += itemPrice;
            }

            User user = booking.getUser();
            String userEmail = user.getEmail();

            String bookingTime= booking.getBookingTime().toString().split("\\.")[0];

            response.put("id", bookingId);
            response.put("stallId", stallId);
            response.put("venueName", venueName);
            response.put("address", address);
            response.put("dateSlot", dateSlot);
            response.put("totalPrice", totalPrice);
            response.put("bookingTime", bookingTime);
            response.put("status", status);
            response.put("userEmail", userEmail);

            if(status.equals("Booked")){
                responseBooked.add(response);
            }else {
                responseElse.add(response);
            }
        }
        responses.add(responseBooked);
        responses.add(responseElse);
        return responses;
    }

    @GetMapping("/own")
    @ValidateUserType
    @ResponseBody
    @Operation(summary = "Find all booking history from current user", description = "user check his booking history")
    public List<List<Map<String, Object>>> ownBookingHistory() {
        User user = (User) httpSession.getAttribute("currentUser");

        List<Booking> bookingList = bookingRepository.getBookingByUser(user.getId());
        List<List<Map<String, Object>>> responses = new ArrayList<>();
        List<Map<String, Object>> responseBooked = new ArrayList<>();
        List<Map<String, Object>> responseElse = new ArrayList<>();

        for (Booking booking: bookingList) {
            Map<String, Object> response = new HashMap<>();
            int bookingId = booking.getId();
            String stallId = booking.getStallDate().getStall().getStallId();
            int venueId = booking.getStallDate().getVenueDate().getVenueId();
            Venue venue = venueRepository.findById(venueId).get();

            String venueName = venue.getVenueName();
            String street = venue.getStreet();
            String suburb = venue.getSuburb();
            String state = venue.getState();
            String address = street+", "+suburb+" "+state;

            Date dateSlot= booking.getStallDate().getVenueDate().getDateSlot();
            String status = booking.getStatus();
            double totalPrice = booking.getStallDate().getStall().getPrice();

            List<Payment> paymentList= booking.getPayments();
            for (Payment payment: paymentList) {
                double itemPrice = payment.getAmount();
                totalPrice += itemPrice;
            }

            String bookingTime= booking.getBookingTime().toString().split("\\.")[0];

            response.put("id", bookingId);
            response.put("stallId", stallId);
            response.put("venueName", venueName);
            response.put("address", address);
            response.put("dateSlot", dateSlot);
            response.put("totalPrice", totalPrice);
            response.put("bookingTime", bookingTime);
            response.put("status", status);

            if(status.equals("Booked")){
                responseBooked.add(response);
            }else {
                responseElse.add(response);
            }
        }
        responses.add(responseBooked);
        responses.add(responseElse);
        return responses;
    }

    //payment的账单查询
    @GetMapping("/{bookingId}")
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
