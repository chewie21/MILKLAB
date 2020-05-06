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

    public Order createNewActiveOrderByUser(User user) {
        Order order = new Order(null,null, OrderStatusEnum.ACTIVE, user);
        orderRepo.save(order);
        return order;
    }

    public Iterable<Order> findAll() {
        return orderRepo.findAll();
    }

    public List<Order> findAllByUser(User user) {
        return orderRepo.findAllByUser_Id(user.getId());
    }

    public Order findActiveOrderByUser(User user) {
        Order order = orderRepo.findByUser_IdAndStatus(user.getId(), OrderStatusEnum.ACTIVE);
        return order == null ? createNewActiveOrderByUser(user) : order;
    }

    public void addItemToActiveOrder(User userDetails, Long productId) {
        Order activeOrder = findActiveOrderByUser(userDetails);
        if(activeOrder != null) {
            activeOrder.getItems().add(new Product(productId));
            orderRepo.save(activeOrder);
        }
    }

    public void addItemsToActiveOrder(User userDetails, List<Long> productIds) {
        Order activeOrder = findActiveOrderByUser(userDetails);
        if(activeOrder != null) {
            activeOrder.getItems().addAll(productIds.stream().filter(Objects::nonNull).map(Product::new).collect(Collectors.toList()));
            orderRepo.save(activeOrder);
        }
    }

    public void deleteItemFromActiveOrder(User userDetails, Long id) {
        Order activeOrder = findActiveOrderByUser(userDetails);
        activeOrder.getItems().removeIf(product -> product.getId().equals(id));
        orderRepo.save(activeOrder);
    }
}

