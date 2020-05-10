package com.example.milk.controller;

import com.example.milk.domain.User;
import com.example.milk.repos.UserRepo;
import com.example.milk.service.OrderService;
import com.example.milk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String ShowAccount (@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("active", orderService.findByTrueActive(user, true) );
        model.addAttribute("notActive", orderService.findByFalseActive(user, false));
        return "account";
    }
    @GetMapping("edit")
    public String EditAccount (@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "accountEdit";
    }
    @PostMapping("edit")
    public String SaveAccount (@AuthenticationPrincipal User user,
                               @RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String date,
                               @RequestParam String password) {

        userService.saveAccount(user, name, surname, username, email, date, password);
        return "redirect:/account";
    }

}

