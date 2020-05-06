package com.example.milk.repos;

import com.example.milk.domain.Order;
import com.example.milk.domain.OrderStatusEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepo extends CrudRepository <Order, Long> {
    List<Order> findAllByUser_Id(Long userId);
    List<Order> findAllByUser_IdAndStatus(Long userId, OrderStatusEnum orderStatusEnum);
    Order findByUser_IdAndStatus(Long userId, OrderStatusEnum orderStatusEnum);
}
