package com.usyd.group08.ELEC5619.rest;


import com.usyd.group08.ELEC5619.aop.ValidateUserType;
import com.usyd.group08.ELEC5619.models.User;
import com.usyd.group08.ELEC5619.repositries.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession httpSession;

    @GetMapping("hello")
    @ValidateUserType
    public String people(@RequestParam(value = "name", defaultValue = "World") String name) {
        User user = (User) httpSession.getAttribute("currentUser");
        return "Hello, " + user.getFirstName();
    }

    @GetMapping("organisers")
    @ValidateUserType(type = "admin")
    public List<User> getOrganisers() {
        User user = new User();
        user.setType("organiser");
        return userRepository.findAll(Example.of(user));
    }

    @GetMapping("owners")
    @ValidateUserType(type = "admin")
    public List<User> getOwners() {
        User user = new User();
        user.setType("owner");
        return userRepository.findAll(Example.of(user));
    }

    @PostMapping("register")
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }

}
