package com.usyd.group08.elec5619.rest;

import com.usyd.group08.elec5619.aop.ValidateUserType;
import com.usyd.group08.elec5619.models.User;
import com.usyd.group08.elec5619.models.Venue;
import com.usyd.group08.elec5619.repositries.VenueRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/venues")
public class VenueController {
    @Autowired
    VenueRepository venueRepository;

    @Autowired
    HttpSession httpSession;


    /**
     * Find all Venue
     * @return
     */
    @GetMapping
    @Operation(summary = "Find all venues",description = "Pass venues list, and will return venues list")
    public List<Venue> getVenues(@RequestParam String userID) {
        Venue venue = new Venue();
        User user = new User();
        user.setId(Long.valueOf(userID));
        venue.setUser(user);
//        venue.setUser();
        return venueRepository.findAll(Example.of(venue));

    }

    /**
     * Create Stall Venue
     * @param venue
     * @return
     */
    @PostMapping
//    @ValidateUserType(type = "admin") // 允许 admin 和 organizer
    @Operation(summary = "Add a new venue",description = "Pass in a venue without venueID")
    public Venue addVenue(@RequestBody Venue venue) {
        return venueRepository.save(venue);
    }

    /**
     * Delete venue
     * @param venueID
     * @return
     */
    @DeleteMapping
//    @ValidateUserType(types = {"admin", "organizer"}) // 允许 admin 和 organizer
    @Operation(summary = "Delete venue by venueID", description = "Pass in venueID, and will return true(delete success) or false(delete not success)")
    public boolean deleteVenue(@RequestParam String venueID){
        Optional<Venue> optionalVenue = venueRepository.findById(venueID);
        if(optionalVenue.isPresent()){
            Venue venue = optionalVenue.get();
            venueRepository.delete(venue);
            return true;
        }
        return false;
    }

    /**
     * Update venue information
     * @param pendingUpdateVenue
     * @return
     */
    @PutMapping
//    @ValidateUserType(types = {"admin", "organizer"}) // 允许 admin 和 organizer
    @Operation(summary = "Update venue info", description = "Pass the updated venue object, and will return the updated venue object.")
    public Venue updateVenue(@RequestParam Venue pendingUpdateVenue){
        Optional<Venue> optionalVenue = venueRepository.findById(String.valueOf(pendingUpdateVenue.getId()));
        if(optionalVenue.isPresent()){
            Venue venue = optionalVenue.get();
            venue.setUser(pendingUpdateVenue.getUser());
            venue.setStreet(pendingUpdateVenue.getStreet());
            venue.setSuburb(pendingUpdateVenue.getSuburb());
            venue.setState(pendingUpdateVenue.getState());
            venue.setDescription(pendingUpdateVenue.getDescription());
            venue.setPicture(pendingUpdateVenue.getPicture());
            venue.setLongitude(pendingUpdateVenue.getLongitude());
            venue.setLatitude(pendingUpdateVenue.getLatitude());
            return venue;
        }
        return null;
    }




}
