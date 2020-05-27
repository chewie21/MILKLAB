package com.example.milk.controller;

import com.example.milk.domain.*;
import com.example.milk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    CarouselService carouselService;
    @Autowired
    BasketInfoService basketInfoService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductGroupService productGroupService;

    @GetMapping
    public String showMenu(@AuthenticationPrincipal User user,
                           Map<String, Object> model) {
        model.put("user", user);
        model.put("carousels", carouselService.findAll());
        model.put("groups", productGroupService.findProductGroupByActive());
        model.put("countProducts", basketInfoService.countProduct(user));
        model.put("countOrders", orderService.orderCount());
        return "menu";
    }
}