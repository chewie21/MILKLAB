package com.example.milk.controller;

import com.example.milk.domain.*;
import com.example.milk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BasketInfoService basketInfoService;
    @Autowired
    private InfoService infoService;
    @Autowired
    private DiscountService discountService;

    @GetMapping
    public String showBasket (@AuthenticationPrincipal User user,
                              Map<String, Object> model) {
        model.put("user", user);
        model.put("basketInfo", basketInfoService.findBasketInfo(basketService.findBasket(user)));
        model.put("orderCoast", basketInfoService.orderCoast(user));
        model.put("count", infoService.countAdminActivity());
        model.put("orderCoastDiscount", discountService.discountByReview(user));
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
    @PostMapping("/saveOrderDelivery")
    public String saveOrderDelivery(@AuthenticationPrincipal User user,
                            @RequestParam Long orderCoast,
                            @RequestParam Long orderCoastDiscount,
                            @RequestParam String address,
                            @RequestParam String comment,
                            String time, String date, RedirectAttributes attr) {
        if (orderCoastDiscount != null) {
            orderCoast = orderCoastDiscount;
        }
        date = orderService.dateFormat();
        time = orderService.timeFormat();
        Basket basket = basketService.findByUserId(user);
        OrderStatusEnum orderStatusEnum = OrderStatusEnum.DELIVERY;
        orderService.saveOrder(basket, user, address, comment, time, date, orderCoast, orderStatusEnum);
        basketInfoService.deleteBasketInfo(basket);
        basketService.deleteBasket(user);
        attr.addFlashAttribute("orderSuccess", "Ваш заказ отправлен на кухню");
        return "redirect:/account";
    }
    @PostMapping("/saveOrderPickup")
    public String saveOrderPickup(@AuthenticationPrincipal User user,
                            @RequestParam Long orderCoastDiscount,
                            @RequestParam Long orderCoast,
                            @RequestParam String comment,
                            String time, String date, String address, RedirectAttributes attr) {
        if (orderCoastDiscount != null) {
            orderCoast = orderCoastDiscount;
        }
        date = orderService.dateFormat();
        time = orderService.timeFormat();
        Basket basket = basketService.findByUserId(user);
        OrderStatusEnum orderStatusEnum = OrderStatusEnum.PICKUP;
        orderService.saveOrder(basket, user, address, comment, time, date, orderCoast, orderStatusEnum);
        basketInfoService.deleteBasketInfo(basket);
        basketService.deleteBasket(user);
        attr.addFlashAttribute("orderSuccess", "Ваш заказ отправлен на кухню");
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
