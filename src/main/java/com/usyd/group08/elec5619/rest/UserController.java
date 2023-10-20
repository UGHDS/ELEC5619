package com.usyd.group08.elec5619.rest;


import com.usyd.group08.elec5619.aop.ValidateUserType;
import com.usyd.group08.elec5619.models.Stall;
import com.usyd.group08.elec5619.models.User;
import com.usyd.group08.elec5619.models.Venue;
import com.usyd.group08.elec5619.repositries.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

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
//    @ValidateUserType(type = "admin") //有bug
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
    public User approveOrganiser(@RequestParam String userID){
        Optional<User> optionalUser = userRepository.findById(userID);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setStatus("active");
            userRepository.save(user);
            return user;
        }
        return null;
    }

    @PostMapping("register")
    @Operation(summary = "Register new owner or organiser",description = "Pass in a user without userID")
    public User register(@RequestBody User user) {
        if(user.getType().equals("owner")){
            user.setStatus("active");
        }else {
            user.setStatus("pending");
        }
        return userRepository.save(user);
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
    @ValidateUserType(type = "admin")
    @Operation(summary = "Update user info", description = "Pass the updated user object, and will return the updated user object.")
    public User updateUser(@RequestParam User pendingUpdateUser){
        Optional<User> optionalUser = userRepository.findById(String.valueOf(pendingUpdateUser.getId()));
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setEmail(pendingUpdateUser.getEmail());
            user.setFirstName(pendingUpdateUser.getFirstName());
            user.setLastName(pendingUpdateUser.getLastName());
            user.setPassword(pendingUpdateUser.getPassword());
            user.setPhone(pendingUpdateUser.getPhone());
            return user;
        }
        return null;
    }



}
