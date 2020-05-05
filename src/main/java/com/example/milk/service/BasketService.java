package com.example.milk.service;

import com.example.milk.domain.Basket;
import com.example.milk.domain.Order;
import com.example.milk.domain.Product;
import com.example.milk.domain.User;
import com.example.milk.repos.BasketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    @Autowired
    BasketRepo basketRepo;

    public void deleteBasket (Basket basket) {
        basketRepo.delete(basket);
    }

}
