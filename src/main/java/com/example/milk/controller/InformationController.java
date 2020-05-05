package com.example.milk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InformationController {

    @GetMapping("/information")
    public String information() {
        return "/information";
    }
    @GetMapping("/contacts")
    public String contacts() {
        return "/contacts";
    }
}
