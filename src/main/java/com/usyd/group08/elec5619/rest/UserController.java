package com.usyd.group08.ELEC5619.rest;


import com.usyd.group08.ELEC5619.aop.ValidateUserType;
import com.usyd.group08.ELEC5619.models.User;
import com.usyd.group08.ELEC5619.repositries.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession httpSession;

    @GetMapping("hello")
    @ValidateUserType
    @Operation(summary = "welcome current user",description = "Pass user name, and return 'Hello, current user'")
    public String people(@RequestParam(value = "name", defaultValue = "World") String name) {
        User user = (User) httpSession.getAttribute("currentUser");
        return "Hello, " + user.getFirstName();
    }

    @GetMapping("organisers")
    @ValidateUserType(type = "admin")
    @Operation(summary = "Find all organisers",description = "Pass user list, and will return organisers list")
    public List<User> getOrganisers() {
        User user = new User();
        user.setType("organiser");
        return userRepository.findAll(Example.of(user));
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
