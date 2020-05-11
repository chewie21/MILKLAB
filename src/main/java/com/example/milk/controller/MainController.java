package com.example.milk.controller;

import com.example.milk.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/")
    public String greeting (@AuthenticationPrincipal User user,
                            Map<String, Object> model) {
        model.put("user", user);
        return "main";
    }

}
