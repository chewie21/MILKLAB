package com.example.milk.controller;

import com.example.milk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public String showAdmin (Map<String, Object> model) {
        model.put("count", orderService.orderCount());
        return "/Admin";
    }

}
