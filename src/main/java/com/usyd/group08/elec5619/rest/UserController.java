package com.usyd.group08.elec5619.rest;


import com.usyd.group08.elec5619.aop.ValidateUserType;
import com.usyd.group08.elec5619.models.User;
import com.usyd.group08.elec5619.models.Venue;
import com.usyd.group08.elec5619.repositries.UserRepository;
import com.usyd.group08.elec5619.repositries.VenueRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    HttpSession httpSession;

    @GetMapping("getCurrentUserInfo")
    @ValidateUserType
    @Operation(summary = "get current user info",description = "Pass user name, and return 'Hello, current user'")
    public Map<String, Object> getCurrentUser() {
        User user = (User) httpSession.getAttribute("currentUser");
        Map<String, Object> response = new HashMap<>();

        //判断当前得到的VenueID 是否是属于当前的organiser的，如果是的话删除不是的话返还false, admin可以删除所有的venue
        Long userId = user.getId();
        String userEmail = user.getEmail();
        String first_name = user.getFirstName();
        String last_name = user.getLastName();
        String phone = user.getPhone();
        String status = user.getStatus();
        String type = user.getType();

        response.put("userId", userId);
        response.put("userEmail", userEmail);
        response.put("first_name", first_name);
        response.put("last_name", last_name);
        response.put("phone", phone);
        response.put("status", status);
        response.put("type", type);

        return response;
    }

    @GetMapping("organisers")
    @Operation(summary = "Find all organisers",description = "Pass user list, and will return organisers list")
    public List<Map<String, Object>> getOrganisers() {
        User user = new User();
        user.setType("organiser");
        List<User> organisers = userRepository.findAll(Example.of(user));
        List<Map<String, Object>> responses = new ArrayList<>();

        for (User organiser: organisers) {
            Map<String, Object> response = new HashMap<>();

            Long organiserId = organiser.getId();
            String organiserEmail = organiser.getEmail();
            String first_name = organiser.getFirstName();
            String last_name = organiser.getLastName();
            String phone = organiser.getPhone();
            String status = organiser.getStatus();

            response.put("organiserId", organiserId);
            response.put("organiserEmail", organiserEmail);
            response.put("first_name", first_name);
            response.put("last_name", last_name);
            response.put("phone", phone);
            response.put("status", status);

            responses.add(response);
        }
        return responses;
    }

    @GetMapping("owners")
    @ValidateUserType(type = "admin")
    @Operation(summary = "Find all owners",description = "Pass user list, and will return owners list")
    public List<User> getOwners() {
        User user = new User();
        user.setType("owner");
        return userRepository.findAll(Example.of(user));
    }

    @PutMapping ("organisers/approve")
    @ValidateUserType(type = "admin")
    @Operation(summary = "Approve the organiser by userID",description = "Pass in userID, and return")
    public boolean approveOrganiser(@RequestParam String userID){
        Optional<User> optionalUser = userRepository.findById(userID);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setStatus("active");

            Venue venue = new Venue();
            venue.setVenueName("");
            venue.setState("");
            venue.setStreet("");
            venue.setSuburb("");
            venue.setDescription("");
            venue.setPicture("");
            venue.setUser(user);
            venueRepository.save(venue);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @PostMapping("register")
    @Operation(summary = "Register new owner or organiser",description = "Pass in a user without userID")
    public RedirectView register(@RequestParam String firstName, String lastName, String phone, String email, String password, String type) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPhone(phone);
        newUser.setPassword(password);
        newUser.setType(type);

        if (type == null){
            return new RedirectView("/registerSuccess");
        }

        if(type.equals("owner")){
            newUser.setStatus("active");
        }else {
            newUser.setStatus("pending");
        }
        userRepository.save(newUser);
        return new RedirectView("/registerSuccess");  // Redirect to a success page or any other URL after registration
    }

    @DeleteMapping
    @ValidateUserType(type = "admin")
    @Operation(summary = "Delete user by userID", description = "Pass in userID, and will return true(delete success) or false(delete not success)")
    public boolean deleteUser(@RequestParam String userID){
        Optional<User> optionalUser = userRepository.findById(userID);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    @PutMapping
    @ValidateUserType
    @Operation(summary = "Update user info", description = "Pass the updated user object, and will return the updated user object.")
    public boolean updateUser(@RequestParam String firstName, String lastName, String phone, String email, String userId){
        Optional<User> optionalUser = userRepository.findById(String.valueOf(userId));
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhone(phone);
            userRepository.save(user);  // 保存更改到数据库
            return true;
        }
        return false;
    }

    @PutMapping("/password")
    @Operation(summary = "Update user info", description = "Pass the updated user object, and will return the updated user object.")
    public boolean updatePassword(@RequestParam String userId, String password){
        Optional<User> optionalUser = userRepository.findById(String.valueOf(userId));
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setPassword(password);
            userRepository.save(user);  // 保存更改到数据库
            return true;
        }
        return false;
    }
}
