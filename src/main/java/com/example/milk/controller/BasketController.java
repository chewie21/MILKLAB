package com.example.milk.controller;

import com.example.milk.domain.Product;
import com.example.milk.domain.ProductForm;
import com.example.milk.domain.User;
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

    @GetMapping
    public String showBasket (@AuthenticationPrincipal User user,
                              Map<String, Object> model) {
        model.put("user", user);
        model.put("baskets", basketService.findBasket(user));
        model.put("orderCoast", basketService.orderCoast(user));
        return "basket";
    }
    @PostMapping
    public String newBasket(@AuthenticationPrincipal User user, Long productId,
                            Map<String, Object> model) {
        basketService.editBasket(user, productId);
        model.put("baskets", basketService.findByUserId(user));
        model.put("orderCoast", basketService.orderCoast(user));
        return "basket";
    }
    @PostMapping("/saveOrder")
    public String saveOrder(@AuthenticationPrincipal User user,
                            @RequestParam Long orderCoast,
                            @RequestParam String address,
                            @ModelAttribute ProductForm productForm,
                            String time, String date) {
        Date nowDate = new Date();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        time = timeFormat.format(nowDate);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        date = dateFormat.format(nowDate);

        List<Long> productIds = productForm.getProductIds();
        orderService.saveOrder(user, address, time, date, orderCoast, productIds);
        basketService.deleteBasket(user);
        return "redirect:/account";
    }
    @GetMapping("/delete/{product}")
    public String deleteProductFromBasket (@AuthenticationPrincipal User user,
                                           @PathVariable Product product) {
        Long productId = product.getId();
        basketService.deleteProductFromBasket(user, productId);
        return "redirect:/basket";
    }
}
