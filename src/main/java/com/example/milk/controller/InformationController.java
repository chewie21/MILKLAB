package com.example.milk.controller;

import com.example.milk.domain.User;
import com.example.milk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InformationController {

    @Autowired
    OrderService orderService;

    @GetMapping("/information")
    public String information(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("countOrders", orderService.orderCount());
        return "/information";
    }
    @GetMapping("/contacts")
    public String contacts(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("countOrders", orderService.orderCount());
        return "/contacts";
    }
}
