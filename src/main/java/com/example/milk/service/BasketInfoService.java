package com.example.milk.service;

import com.example.milk.domain.Basket;
import com.example.milk.domain.BasketInfo;
import com.example.milk.domain.Product;
import com.example.milk.domain.User;
import com.example.milk.repos.BasketInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional
public class BasketInfoService {

    @Autowired
    private BasketInfoRepo basketInfoRepo;
    @Autowired
    private EntityManager entityManager;

    public List<BasketInfo> findBasketInfo (Basket basket) {
        return basketInfoRepo.findAllByBasketId(basket.getId());
    }
    public void newBasketInfo (Basket basket, Long productId) {
        BasketInfo basketInfo = new BasketInfo(basket, null);
        basketInfo.setProduct(new Product(productId));
        basketInfoRepo.save(basketInfo);
        entityManager.refresh(basketInfo);
    }
    public String orderCoast (User user) {
        return basketInfoRepo.orderCoast(user.getId());
    }

    public void deleteProductFromBasket (Long productId, Long basketId) {
        basketInfoRepo.deleteFromBasket(productId, basketId);
    }
    public void deleteBasketInfo (Basket basket) {
        basketInfoRepo.deleteAll(basketInfoRepo.findAllByBasketId(basket.getId()));
    }
    public String countProduct (User user) {
        return user == null ? null : basketInfoRepo.countProducts(user.getId()).equals("0") ? null : basketInfoRepo.countProducts(user.getId());
    }
    public void deleteProduct (Long productId) {
        basketInfoRepo.deleteProduct(productId);
    }
}
