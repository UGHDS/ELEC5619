package com.usyd.group08.elec5619.rest;

import com.usyd.group08.elec5619.aop.ValidateUserType;
import com.usyd.group08.elec5619.models.Stall;
import com.usyd.group08.elec5619.models.StallDate;
import com.usyd.group08.elec5619.models.User;
import com.usyd.group08.elec5619.models.Venue;
import com.usyd.group08.elec5619.repositries.StallDateRepository;
import com.usyd.group08.elec5619.repositries.StallRepository;
import com.usyd.group08.elec5619.repositries.VenueRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.*;

@RestController
@RequestMapping("/api/stalls")
public class StallController {
    @Autowired
    StallRepository stallRepository;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    StallDateRepository stallDateRepository;

    @Autowired
    HttpSession httpSession;

    /**
     * Find all stalls in current venue
     *
     * @return
     */
    @GetMapping
//    @ValidateUserType
    @Operation(summary = "Find all stalls in current venue", description = "Pass venue ID, and will return stalls list in this specific venue")
    public List<Map<String, Object>> getStallsByVenueId(@RequestParam String venueID) {
        List<Map<String, Object>> responses = new ArrayList<>();
        Optional<Venue> venue = venueRepository.findById(Integer.parseInt(venueID));
        if (venue.isPresent()) {
            List<Stall> stallList = venue.get().getStalls();

            for (Stall stall: stallList) {
                List<StallDate> stallDateList = stallDateRepository.getStallDateByStallId(stall.getId());
                for (StallDate stallDate: stallDateList) {
                    if (stallDate.getStatus().equals("Available")){
                        Map<String, Object> response = new HashMap<>();
                        response.put("stallId", stall.getId());
                        response.put("stallName", stall.getStallId());
                        response.put("stallPrice", stall.getPrice());
                        response.put("dateSlot", stallDate.getVenueDate().getDateSlot());
                        responses.add(response);
                    }
                }
            }
        }
        return responses;
    }

    @PostMapping
    @Operation(summary = "Add stalls into venue by id", description = "Pass a list of stalls in post body and a " +
            "venue id in the query. venue_id must from current user. [NOTES] venue_id in 'Request body' can skip and will be ignored")
    public boolean addStalls(@RequestParam String venueId, String price, String stallName) {
        try{
            Stall newStall = new Stall();
            newStall.setVenueId(Integer.valueOf(venueId));
            newStall.setPrice(Double.valueOf(price));
            newStall.setStallId(stallName);
            stallRepository.save(newStall);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @DeleteMapping
    @Operation(summary = "Delete stall by venue id and stall id", description = "Pass venue id and stall id, stall will be deleted")
    public boolean deleteStall(@RequestParam int venueId, @RequestParam String stallId) {
        User user = (User) httpSession.getAttribute("currentUser");
        Optional<Venue> venue = venueRepository.findById(venueId);
        if (user.getType().equals("admin") || venue.isPresent() && venue.get().getUser().getId().equals(user.getId())) {
            for (Stall stall : venue.get().getStalls()) {
                if (stall.getStallId().equals(stallId)) {
                    stallRepository.delete(stall);
                    return true;
                }
            }
        }
        return false;
    }

    @PutMapping
    @ValidateUserType(type = "admin,organiser")
    @Operation(summary = "Change stall price", description = "Pass stall obj including new price, will update new stall price")
    public boolean updateStallPrice(@RequestBody Stall stall) {
        User user = (User) httpSession.getAttribute("currentUser");
        Optional<Venue> venue = venueRepository.findById(stall.getVenueId());
        if (user.getType().equals("admin") || venue.isPresent() && venue.get().getUser().getId().equals(user.getId())) {
            for (Stall theStall : venue.get().getStalls()) {
                if (theStall.getStallId().equals(stall.getId())) {
                    stallRepository.save(stall);
                    return true;
                }
            }
        }
        return false;
    }

    @GetMapping("/{id}/stallDates")
    @ValidateUserType
    @Operation(summary = "get stall dates by stall id", description = "Pass stall id and will return all stall dates")
    public List<StallDate> getStallDates(@PathVariable int id) {
        return stallDateRepository.getStallDateByStallId(id);
    }
}
