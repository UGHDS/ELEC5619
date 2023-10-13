package com.usyd.group08.elec5619.controllers;

import com.usyd.group08.elec5619.models.Result;
import com.usyd.group08.elec5619.models.User;
import com.usyd.group08.elec5619.repositries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @ResponseBody
    public Result login(@RequestParam String email, @RequestParam String password, Model model) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        Optional<User> currentUser = userRepository.findOne(Example.of(user));
        Map<String, Integer> result = new HashMap<>();
        if(currentUser.isPresent()){
            model.addAttribute("currentUser", currentUser.get());
            String userType = currentUser.get().getType();
            int tyoeNum = -1;

            if(userType.equals("owner")){
                tyoeNum = 0;
            }
            else if(userType.equals("organiser")){
                tyoeNum = 1;
            }
            else if(userType.equals("admin")){
                tyoeNum = 2;
            }
//            List<Dept> deptList = deptService.list();
            result.put("tyoeNum", tyoeNum);
            return Result.success(result);
        }
        return Result.error("Fail to log in");
    }

}
