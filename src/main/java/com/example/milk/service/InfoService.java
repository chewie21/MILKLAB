package com.example.milk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ReviewService reviewService;

    public int countAdminActivity () {
        int orders = 0;
        int users = 0;
        int products = 0;
        int reviews = 0;
        if (orderService.countActiveOrders() != null) {
             orders = Integer.parseInt(orderService.countActiveOrders());
        }
        if (userService.countNotActiveUsers() !=null) {
             users = Integer.parseInt(userService.countNotActiveUsers());
        }
        if (productService.countStopProducts() != null) {
             products = Integer.parseInt(productService.countStopProducts());
        }
        if (reviewService.countNewReviews() !=null) {
            reviews = Integer.parseInt(reviewService.countNewReviews());
        }
        return orders + users + products + reviews;
    }
}
