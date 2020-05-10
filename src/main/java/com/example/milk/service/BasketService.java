package com.example.milk.service;

import com.example.milk.domain.Basket;
import com.example.milk.domain.Product;
import com.example.milk.domain.User;
import com.example.milk.repos.BasketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

    @Autowired
    BasketRepo basketRepo;
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

    public void editBasket(User user, Long productId) {
        Basket basket = findBasket(user);
        if (basket != null) {
            basket.getProducts().add(new Product(productId));
            basketRepo.save(basket);
        }
    }

    public String orderCoast (User user) {
        return basketRepo.orderCoast(user.getId());
    }

    public void deleteProductFromBasket(User user, Long productId) {
        Basket basket = findBasket(user);
        Long x = basket.getId();
        basket.getProducts().removeIf(product -> product.getId().equals(productId));
        basketRepo.save(basket);
    }
    public void deleteBasket (User user) {
        Basket basket = findBasket(user);
        basketRepo.delete(basket);
    }
}
