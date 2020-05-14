package com.example.milk.controller;

import com.example.milk.domain.*;
import com.example.milk.repos.BasketRepo;
import com.example.milk.repos.OrderRepo;
import com.example.milk.repos.ProductRepo;
import com.example.milk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    ProductService productService;
    @Autowired
    CarouselService carouselService;
    @Autowired
    ProductGroupService productGroupService;
    @Autowired
    BasketInfoService basketInfoService;

    @GetMapping
    public String showMenu(@AuthenticationPrincipal User user,
                           Map<String, Object> model) {
        model.put("user", user);
        model.put("products", productService.findAll());
        model.put("carousels", carouselService.findAll());
        model.put("groups", productGroupService.findAllGroup());
        model.put("count", basketInfoService.countProduct(user));
        return "menu";
    }
}