package com.example.milk.service;

import com.example.milk.domain.Order;
import com.example.milk.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    public Iterable<Order> findAll() {
        return orderRepo.findAll();
    }

}
