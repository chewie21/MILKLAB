package com.example.milk.controller;

import com.example.milk.domain.*;
import com.example.milk.repos.BasketRepo;
import com.example.milk.repos.OrderRepo;
import com.example.milk.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    BasketRepo basketRepo;


    @GetMapping
    public String showMenu (Map<String, Object> model) {
        model.put("products", productRepo.findAll());
        return "menu";
    }
    @GetMapping("/basket")
    public String showBasket (@AuthenticationPrincipal User user,
                             Map<String, Object> model) {
        model.put("baskets", basketRepo.findAllByUserId(user.getId()));
        return "basket";
    }
    @GetMapping("/basket/{product}")
    public String newBasket(@AuthenticationPrincipal User user,
                            @PathVariable Product product,
                            Map<String, Object> model) {
        Basket basket = new Basket(user, product);
        basketRepo.save(basket);
        model.put("baskets", basketRepo.findAllByUserId(user.getId()));
        return "basket";
    }

    @PostMapping("/order")
    public String saveOrder (@AuthenticationPrincipal User user,
                             @RequestParam String orderCoast,
                             @RequestParam String address,
                             String time, String date) {
        Date nowDate = new Date();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        time = timeFormat.format(nowDate);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        date = dateFormat.format(nowDate);

        return "menu";
    }

    @PostMapping("/basket/{basket}")
    public String deleteBasket (@PathVariable Basket basket) {

        return "redirect:/menu/basket";
    }
}
