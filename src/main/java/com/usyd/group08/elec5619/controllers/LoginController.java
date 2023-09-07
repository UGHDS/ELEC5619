package com.usyd.group08.elec5619.controllers;

import com.usyd.group08.elec5619.models.User;
import com.usyd.group08.elec5619.repositries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@SessionAttributes({"currentUser"})
public class LoginController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/login")
    public String index(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Optional<User> currentUser = userRepository.findOne(Example.of(user));
        if(currentUser.isPresent()){
            model.addAttribute("currentUser", currentUser.get());
            return "index";
        }
        return "login";
    }


}
