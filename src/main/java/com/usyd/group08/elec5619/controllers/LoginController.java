package com.usyd.group08.elec5619.controllers;

//import com.usyd.group08.elec5619.models.Result;
import com.usyd.group08.elec5619.models.User;
import com.usyd.group08.elec5619.repositries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.*;

@Controller
@SessionAttributes({"currentUser"})
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession httpSession;

    @GetMapping("/login")
    public String index(Model model) {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestParam String email, @RequestParam String password, Model model) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Optional<User> currentUser = userRepository.findOne(Example.of(user));

        Map<String, Object> response = new HashMap<>();
        if(currentUser.isPresent()){//用户名，密码正确
            User curUser = currentUser.get();

            model.addAttribute("currentUser", curUser);
            // 使用HttpSession来设置属性
            httpSession.setAttribute("currentUser", curUser);

            response.put("userType", curUser.getType());
            response.put("status", curUser.getStatus());
            response.put("firstName", curUser.getFirstName());
            response.put("lastName", curUser.getLastName());
            return response;

        } else {//用户名，密码错误
//            return Result.error("Invalid username or password");
            response.put("error", "login error");
            return response;
        }
    }

    //仍有bug
    @DeleteMapping("/logout")
    @ResponseBody
    public Map<String, Object> logout(Model model) {
        Map<String, Object> response = new HashMap<>();
        model.asMap().remove("currentUser");

        // Remove 'currentUser' from the HttpSession
        httpSession.removeAttribute("currentUser");

//            httpSession.invalidate();
        response.put("msg", "login out success");
        response.put("code", 200);

        return response;
    }
}