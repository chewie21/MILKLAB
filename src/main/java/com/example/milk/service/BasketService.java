package com.example.milk.service;

import com.example.milk.domain.Basket;
import com.example.milk.domain.BasketInfo;
import com.example.milk.domain.Product;
import com.example.milk.domain.User;
import com.example.milk.repos.BasketInfoRepo;
import com.example.milk.repos.BasketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

    @Autowired
    BasketRepo basketRepo;
    @Autowired
    BasketInfoRepo basketInfoRepo;

    public Basket findByUserId(User user) {
        return basketRepo.findByUserId(user.getId());
    }

    public Basket newBasket(User user) {
        Basket basket = new Basket(user);
        basketRepo.save(basket);
        return basket;
    }
    public Basket findBasket (User user) {
        Basket basket = basketRepo.findByUserId(user.getId());
        return basket == null ? newBasket(user) : basket;
    }
    public void deleteBasket (User user) {
        basketRepo.delete(basketRepo.findByUserId(user.getId()));
    }


}
