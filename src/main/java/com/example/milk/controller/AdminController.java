package com.example.milk.controller;

import com.example.milk.service.OrderService;
import com.example.milk.service.ProductService;
import com.example.milk.service.ReviewService;
import com.example.milk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public String showAdmin (Map<String, Object> model) {
        model.put("countNotActiveOrders", orderService.countActiveOrders());
        model.put("countNotActiveUsers", userService.countNotActiveUsers());
        model.put("countStopProducts", productService.countStopProducts());
        model.put("countNewReviews", reviewService.countNewReviews());
        return "Admin";
    }

}
