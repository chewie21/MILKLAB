package com.example.milk.controller;

import com.example.milk.domain.User;
import com.example.milk.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class InformationController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/")
    public String greeting (@AuthenticationPrincipal User user,
                            Map<String, Object> model) {
        model.put("user", user);
        if (user != null) {
            model.put("count", infoService.countAdminActivity());
        }
        return "main";
    }

    @GetMapping("/information")
    public String information(@AuthenticationPrincipal User user,
                              Map<String, Object> model) {
        model.put("user", user);
        if (user != null) {
            model.put("count", infoService.countAdminActivity());
        }
        return "information";
    }

    @GetMapping("/contacts")
    public String contacts(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        if (user != null) {
            model.addAttribute("count", infoService.countAdminActivity());
        }
        return "contacts";
    }
}
