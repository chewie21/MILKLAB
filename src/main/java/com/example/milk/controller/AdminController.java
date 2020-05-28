package com.example.milk.controller;

import com.example.milk.service.OrderService;
import com.example.milk.service.ProductService;
import com.example.milk.service.UserService;
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
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public String showAdmin (Map<String, Object> model) {
        model.put("countNotActiveOrders", orderService.countActiveOrders());
        model.put("countNotActiveUsers", userService.countNotActiveUsers());
        model.put("countStopProducts", productService.countStopProducts());
        return "/Admin";
    }

}
