package com.example.milk.service;

import com.example.milk.domain.Order;
import com.example.milk.domain.OrderStatusEnum;
import com.example.milk.domain.Product;
import com.example.milk.domain.User;
import com.example.milk.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    public Iterable<Order> findAll() {
        return orderRepo.findAll();
    }
    public Order findById (Long id) { return orderRepo.findById(id).get();}

    public void saveOrder (User user, String address, String time, String date, Long orderCoast, List<Long> productIds) {
        Order order = findOrder(user, address, time, date, orderCoast);
        order.setActive(true);
        order.getProduct().addAll(productIds.stream().filter(Objects::nonNull).map(Product::new).collect(Collectors.toList()));
        order.setStatus(OrderStatusEnum.DONE);
        orderRepo.save(order);

    }

    public Order newOrder (User user, String address, String time, String date, Long orderCoast) {
        Order order = new Order (user, address, time, date, orderCoast, OrderStatusEnum.ACTIVE);
        orderRepo.save(order);
        return order;
    }
    public Order findOrder (User user, String address, String time, String date, Long orderCoast) {
        Order order = orderRepo.findByUserIdAndStatus(user.getId(), OrderStatusEnum.ACTIVE);
        return order == null ? newOrder(user, address, time, date, orderCoast) : order;
    }
    public List<Order> findByTrueActive (User user, boolean active) {
        return orderRepo.findAllByUserIdAndActive(user.getId(), true);
    }
    public List<Order> findByFalseActive (User user, boolean active) {
        return orderRepo.findAllByUserIdAndActive(user.getId(), false);
    }
    public void deleteOrder (Long id) {
        Order order = orderRepo.findById(id).get();
        orderRepo.delete(order);
    }
    public void activeOrder (Long id) {
        Order order = orderRepo.findById(id).get();
        order.setActive(false);
        orderRepo.save(order);
    }
    public List<Order> activeOrders (boolean active) {
        return orderRepo.findAllByActive(active);
    }
}

