package com.example.milk.service;

import com.example.milk.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BasketInfoService basketInfoService;

    public String discountByReview (User user) {
        if (basketInfoService.orderCoast(user) != null) {
            if (reviewService.countReviewsByUsername(user)) {
                int discount = 5; //скидка в 5%
                int orderCoast = Integer.parseInt(basketInfoService.orderCoast(user));
                int orderDiscountCoast = orderCoast - ((orderCoast * discount) / 100);
                return String.valueOf(orderDiscountCoast);
            } else {
                return null;
            }
        }
        else return null;
    }
}
