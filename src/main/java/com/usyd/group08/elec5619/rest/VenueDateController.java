package com.usyd.group08.elec5619.rest;

import com.usyd.group08.elec5619.aop.ValidateUserType;
import com.usyd.group08.elec5619.models.*;
import com.usyd.group08.elec5619.repositries.StallDateRepository;
import com.usyd.group08.elec5619.repositries.StallRepository;
import com.usyd.group08.elec5619.repositries.VenueDateRepository;
import com.usyd.group08.elec5619.repositries.VenueRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/venueDate")
public class VenueDateController {
    @Autowired
    VenueRepository venueRepository;

    @Autowired
    VenueDateRepository venueDateRepository;

    @Autowired
    HttpSession httpSession;

    @GetMapping
    @Operation(summary = "show all venue open dates",description = "Pass venue id, will return all open dates")
    public List<VenueDate> getVenueDates(@RequestParam int venueId){
        Optional<Venue> venue = venueRepository.findById(venueId);
        if(venue.isPresent()){
            return venue.get().getVenueDates();
        }
        return new ArrayList<>();
    }

    @PostMapping
    @ValidateUserType(type = "admin,organiser")
    @Operation(summary = "Add venue dates into venue",description = "Pass venue id and venue date list, will be saved in current venue")
    public boolean addVenueDates(@RequestParam int venueId, @RequestBody List<java.sql.Date> venueDates){
        User user = (User) httpSession.getAttribute("currentUser");
        Optional<Venue> venue = venueRepository.findById(venueId);
        if(user.getType().equals("admin") || venue.isPresent() && venue.get().getUser().getId().equals(user.getId())){
            for(java.sql.Date date : venueDates){
                VenueDate vd = new VenueDate();
                vd.setVenueId(venueId);
                vd.setDateSlot(date);
                venueDateRepository.save(vd);
            }
            return true;
        }
        return false;
    }



}
