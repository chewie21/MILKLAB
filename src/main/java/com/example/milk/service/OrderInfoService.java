package com.example.milk.service;

import com.example.milk.domain.Basket;
import com.example.milk.domain.Order;
import com.example.milk.domain.OrderInfo;
import com.example.milk.repos.OrderInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInfoService {
    @Autowired
    private OrderInfoRepo orderInfoRepo;

    public void newOrderInfo (Basket basket) {
        orderInfoRepo.ProductToOrderInfo(basket.getId());
    }
    public List<OrderInfo> findByOrderId (Long orderId) {
        return orderInfoRepo.findByOrderId(orderId);
    }
    public void deleteFromOrderProductsByOrder (Order order) {
        orderInfoRepo.deleteFromOrderProductsByOrder(order.getId());
    }
}
