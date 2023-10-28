package com.usyd.group08.elec5619.rest;

import com.usyd.group08.elec5619.aop.ValidateUserType;
import com.usyd.group08.elec5619.models.Stall;
import com.usyd.group08.elec5619.models.User;
import com.usyd.group08.elec5619.models.Venue;
import com.usyd.group08.elec5619.repositries.UserRepository;
import com.usyd.group08.elec5619.repositries.VenueRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/venues")
public class VenueController {
    @Autowired
    VenueRepository venueRepository;

    @Autowired
    HttpSession httpSession;

    @Autowired
    UserRepository userRepository;


    /**
     * Find all Venue
     *
     * @return List of all venues
     */
    @GetMapping
    @Operation(summary = "Find all venues", description = "Returns a list of all venues")
    public List<Map<String, Object>> getVenues() {
        List<Venue> venuesList = venueRepository.findAll();

        List<Map<String, Object>> responses = new ArrayList<>();

        for (Venue venue: venuesList) {
            Map<String, Object> response = new HashMap<>();
            int venueId = venue.getId();
            String venueName = venue.getVenueName();
            String street = venue.getStreet();
            String suburb = venue.getSuburb();
            String state = venue.getState();
            String description = venue.getDescription();

            String address = street+", "+suburb+" "+state;
            int stallNum = venue.getStalls().size();
            String image = venue.getPicture();

            response.put("id", venueId);
            response.put("name", venueName);
            response.put("suburb", suburb);
            response.put("street", street);
            response.put("address", address);
            response.put("state", state);
            response.put("stall", stallNum);
            response.put("image", image);
            response.put("latitude", venue.getLatitude());
            response.put("longitude", venue.getLongitude());
            response.put("description", description);
            responses.add(response);
        }
        return responses;
    }

    /**
     * Find venue by Venueid
     *
     * @return that venue
     */
    @GetMapping("/venueByVenueId")
    @Operation(summary = "Find venue by VenueId", description = "Returns that venue")
    public Map<String, Object> getVenueById(@RequestParam String venueID) {
        Optional<Venue> optionalVenue = venueRepository.findById(Integer.parseInt(venueID));

        Map<String, Object> response = new HashMap<>();

        if (optionalVenue.isPresent()) {
            Venue venue = optionalVenue.get();
            //判断当前得到的VenueID 是否是属于当前的organiser的，如果是的话删除不是的话返还false, admin可以删除所有的venue
            int venueId = venue.getId();
            String venueName = venue.getVenueName();
            String street = venue.getStreet();
            String suburb = venue.getSuburb();
            String state = venue.getState();
            String address = street+", "+suburb+" "+state;
            int stallNum = venue.getStalls().size();
            String image = venue.getPicture();
            String description = venue.getDescription();
            List<Stall> stalls = venue.getStalls();

            int sum = 0;
            for (Stall stall: stalls) {
                sum+=stall.getPrice();
            }
            int stallPrice = sum/ stalls.size();

            response.put("id", venueId);
            response.put("name", venueName);
            response.put("suburb", suburb);
            response.put("address", address);
            response.put("stall", stallNum);
            response.put("image", image);
            response.put("description", description);
            response.put("stall_price", stallPrice);
            response.put("stalls", stalls);

        }else{
            response.put("code", 404);
            response.put("msg", "Can not find venue by Id");
        }
        return response;
    }


    /**
     * Find all venues for currentUser
     *
     * @return currentUser's venues
     */
    @GetMapping("/venueByCurrentUser")
    @ValidateUserType(type = "admin,organiser") // 允许 admin 和 organiser
    @Operation(summary = "Find all venues for currentUser", description = "Returns currentUser's venues")
    public List<Map<String, Object>> getVenueByCurrentUser() {
        User user = (User) httpSession.getAttribute("currentUser");
        List<Venue> venuesList = venueRepository.findAll();
        List<Map<String, Object>> responses = new ArrayList<>();
        for (Venue venue: venuesList) {
            if(user.getType().equals("admin") || Objects.equals(user.getId(), venue.getUser().getId())){
                Map<String, Object> response = new HashMap<>();
                int venueId = venue.getId();
                String venueName = venue.getVenueName();
                String street = venue.getStreet();
                String suburb = venue.getSuburb();
                String state = venue.getState();
                String description = venue.getDescription();
                String address = street+", "+suburb+" "+state;
                int stallNum = venue.getStalls().size();
                String image = venue.getPicture();

                response.put("id", venueId);
                response.put("name", venueName);
                response.put("suburb", suburb);
                response.put("street", street);
                response.put("state", state);
                response.put("description", description);
                response.put("address", address);
                response.put("stall", stallNum);
                response.put("image", image);
                response.put("latitude", venue.getLatitude());
                response.put("longitude", venue.getLongitude());
                responses.add(response);
            }
        }
        return responses;
    }


//    @PostMapping
//    @Operation(summary = "Add a new venue", description = "Pass in a venue without venueID")
//    public boolean addVenue(String userId) {
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isPresent()){
//            Venue venue = new Venue();
//            venue.setVenueName("");
//            venue.setState("");
//            venue.setStreet("");
//            venue.setSuburb("");
//            venue.setDescription("");
//            venue.setPicture("");
//            venue.setUser(user.get());
//            venueRepository.save(venue);
//            return true;
//        }
//        return false;
//    }

    /**
     * Delete venue
     *
     * @param venueID
     * @return
     */
    @DeleteMapping
    @ValidateUserType(type = "admin,organiser") // 只允许登陆的用户中的： admin 和 organiser执行此次操作
    @Operation(summary = "Delete venue by venueID", description = "Pass in venueID, and will return true(delete success) or false(delete not success)")
    public boolean deleteVenue(@RequestParam String venueID) {
        User user = (User) httpSession.getAttribute("currentUser");
        Optional<Venue> optionalVenue = venueRepository.findById(Integer.parseInt(venueID));
        if (optionalVenue.isPresent()) {
            Venue venue = optionalVenue.get();
            //判断当前得到的VenueID 是否是属于当前的organiser的，如果是的话删除不是的话返还false, admin可以删除所有的venue
            if (venue.getUser().getId().equals(user.getId()) || user.getType().equals("admin")) {
                venueRepository.delete(venue);
                return true;
            }
        }
        return false;
    }

    @PutMapping
    @Operation(summary = "Update venue info", description = "Pass the updated venue object, and will return the updated venue object.")
    public boolean updateVenue(@RequestParam String venueId, String latitude, String longitude, String description, String picture, String state, String street, String suburb, String venueName) {
        Optional<Venue> optionalVenue = venueRepository.findById(Integer.valueOf(venueId));
        if (optionalVenue.isPresent()) {
            Venue venue = optionalVenue.get();
            venue.setStreet(street);
            venue.setSuburb(suburb);
            venue.setState(state);
            venue.setDescription(description);
            venue.setPicture(picture);
            venue.setLongitude(Double.valueOf(longitude));
            venue.setLatitude(Double.valueOf(latitude));
            venue.setVenueName(venueName);
            venueRepository.save(venue);
            return true;
        }
        return false;
    }

}
