package com.usyd.group08.elec5619.rest;


import com.usyd.group08.elec5619.aop.ValidateUserType;
import com.usyd.group08.elec5619.models.Booking;
import com.usyd.group08.elec5619.models.Payment;
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
@RequestMapping("/api/payments")
public class PaymentController {
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
    @Operation(summary = "User payment", description = "Pass BookingId, specific name and amount")
    public boolean payment(@RequestParam int bookingId, @RequestBody List<PaymentWrapper> paymentWrapperList) {
        User user = (User) httpSession.getAttribute("currentUser");
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (bookingOptional.isPresent() && bookingOptional.get().getUser().getId().equals(user.getId())) {
            for(PaymentWrapper paymentWrapper : paymentWrapperList){
                Payment payment = new Payment();
                payment.setPaymentItem(paymentWrapper.getItemName());
                payment.setBookingId(bookingId);
                payment.setPaymentDate(Timestamp.valueOf(LocalDateTime.now()));
                payment.setAmount(paymentWrapper.getAmount());
                paymentRepository.save(payment);
            }
            return true;
        }
        return false;
    }

    private static class PaymentWrapper {
        private String itemName;
        private double amount;


        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }

}
