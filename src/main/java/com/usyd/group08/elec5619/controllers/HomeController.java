package com.usyd.group08.elec5619.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"currentUser"})
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/uhome")
    public String uhome() {
        return "userhome";
    }

    @GetMapping("/ohome")
    public String ohome() {
        return "userhomeOrganizer";
    }

    @GetMapping("/ahome")
    public String ahome() {
        return "userhomeAdmin";
    }

    @GetMapping("/marketAdmin")
    public String marketAdmin() {
        return "marketAdmin";
    }

    @GetMapping("/marketInfo")
    public String marketInfo() {
        return "marketInfo";
    }

    @GetMapping("/marketList")
    public String marketList() {
        return "marketList";
    }

    @GetMapping("/setting")
    public String setting() {
        return "setting";
    }

    @GetMapping("/registerSuccess")
    public String registerSuccess() {
        return "registerSuccess";
    }


}
