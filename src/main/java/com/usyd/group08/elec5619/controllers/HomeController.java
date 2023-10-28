package com.usyd.group08.elec5619.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"currentUser"})
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/uhome")
    public String uhome(Model model) {
        return "userhome";
    }

    @GetMapping("/ohome")
    public String ohome(Model model) {
        return "userhomeOrganizer";
    }

    @GetMapping("/ahome")
    public String ahome(Model model) {
        return "userhomeAdmin";
    }

/////////////
    @GetMapping("/marketAdmin")
    public String marketAdmin(Model model) {
        return "marketAdmin";
    }
    @GetMapping("/marketInfo")
    public String marketInfo(Model model) {
        return "marketInfo";
    }

    @GetMapping("/marketList")
    public String marketList(Model model) {
        return "marketList";
    }
    @GetMapping("/setting")
    public String setting(Model model) {
        return "setting";
    }

    @GetMapping("/registerSuccess")
    public String registerSuccess(Model model) {
        return "registerSuccess";
    }


}
