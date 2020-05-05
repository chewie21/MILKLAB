package com.example.milk.controller;

import com.example.milk.domain.Order;
import com.example.milk.domain.User;
import com.example.milk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/get-all-by-user")
    public List<Order> getByUser(@AuthenticationPrincipal User userDetails) {
        return orderService.findAllByUser(userDetails);
    }

    @GetMapping("/active/get-by-user")
    public Order getActiveByUser(@AuthenticationPrincipal User userDetails) {
        return orderService.findActiveOrderByUser(userDetails);
    }

    @PostMapping("/active/add-item")
    public void addItemToActiveOrder(@AuthenticationPrincipal User userDetails, Long productId) {
        orderService.addItemToActiveOrder(userDetails, productId);
    }

    @PostMapping("/active/add-items")
    public void addItemsToActiveOrder(@AuthenticationPrincipal User userDetails, List<Long> productIds) {
        orderService.addItemsToActiveOrder(userDetails, productIds);
    }

    @PostMapping("/active/delete-item")
    public void deleteItemFromActiveOrder(@AuthenticationPrincipal User userDetails, Long id) {
        orderService.deleteItemFromActiveOrder(userDetails, id);
    }
}
