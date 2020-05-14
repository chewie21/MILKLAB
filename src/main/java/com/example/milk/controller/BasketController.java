package com.example.milk.controller;

import com.example.milk.domain.*;
import com.example.milk.service.BasketInfoService;
import com.example.milk.service.BasketService;
import com.example.milk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    BasketService basketService;
    @Autowired
    OrderService orderService;
    @Autowired
    BasketInfoService basketInfoService;


    @GetMapping
    public String showBasket (@AuthenticationPrincipal User user,
                              Map<String, Object> model) {
        model.put("user", user);
        model.put("basketInfo", basketInfoService.findBasketInfo(basketService.findBasket(user)));
        model.put("orderCoast", basketInfoService.orderCoast(user));
        return "basket";
    }
    @GetMapping("{product}")
    public String newBasket(@AuthenticationPrincipal User user,
                            @PathVariable Product product,
                            Map<String, Object> model) {
        basketInfoService.newBasketInfo(basketService.findBasket(user), product.getId());
        model.put("user", user);
        model.put("basketInfo", basketInfoService.findBasketInfo(basketService.findBasket(user)));
        model.put("orderCoast", basketInfoService.orderCoast(user));
        return "redirect:/basket";
    }
    @PostMapping("/saveOrder")
    public String saveOrder(@AuthenticationPrincipal User user,
                            @RequestParam Long orderCoast,
                            @RequestParam String address,
                            String time, String date) {
        Date nowDate = new Date();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        time = timeFormat.format(nowDate);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        date = dateFormat.format(nowDate);

        Basket basket = basketService.findByUserId(user);
        List<Long> productIds = basketInfoService.findProductId(basket);
        orderService.saveOrder(user, address, time, date, orderCoast, productIds);
        basketInfoService.deleteBasketInfo(basket);
        basketService.deleteBasket(user);
        return "redirect:/account";
    }
    @GetMapping("/delete/{product}")
    public String deleteProductFromBasket (@AuthenticationPrincipal User user,
                                            @PathVariable Product product) {
        Basket basket = basketService.findByUserId(user);
        basketInfoService.deleteProductFromBasket(product.getId(), basket.getId());
        return "redirect:/basket";
    }
}
