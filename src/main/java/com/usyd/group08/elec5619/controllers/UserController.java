package com.usyd.group08.ELEC5619.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("hello")
    public String people(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello, " + name;
    }
}
