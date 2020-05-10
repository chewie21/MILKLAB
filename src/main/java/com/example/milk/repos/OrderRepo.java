package com.example.milk.repos;

import com.example.milk.domain.Order;
import com.example.milk.domain.OrderStatusEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends CrudRepository <Order, Long> {
    List<Order> findAllByUserIdAndActive(Long userId, boolean active);
    Order findByUserIdAndStatus (Long user, OrderStatusEnum orderStatusEnum);
    List<Order> findAllByActive (boolean active);
}
