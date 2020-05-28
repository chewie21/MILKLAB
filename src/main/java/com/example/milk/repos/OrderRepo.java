package com.example.milk.repos;

import com.example.milk.domain.Order;
import com.example.milk.domain.OrderStatusEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends CrudRepository <Order, Long> {
    Order findByUserIdAndStatus (Long user, OrderStatusEnum orderStatusEnum);

    List<Order> findAllByUserIdAndActive(Long userId, boolean active);
    List<Order> findAllByActive (boolean active);
    List<Order> findAllById (Long id);
    List<Order> findAllByUserUsername (String username);
    List<Order> findAllByDate (String date);
    List<Order> findAllByOrderCoast(Long orderCoast);
    List<Order> findAllByStatus (OrderStatusEnum status);

    @Query(nativeQuery = true,
            value = "select count(*) from m_order where active = true")
    String countActiveOrders ();

    @Query(nativeQuery = true,
            value = "SELECT * FROM m_order where user_id =:user and id = (select max(id) from m_order where user_id =:user)")
    Order lastOrder (@Param("user") Long user);

    @Query(nativeQuery = true,
            value = "select count(*) from m_order where user_id =:user and active = true")
    String countUserActiveOrders (@Param("user") Long user);
    @Query(nativeQuery = true,
            value = "select count(*) from m_order where user_id =:user and active = false ")
    String countUserNotActiveOrders (@Param("user") Long user);

    @Query(nativeQuery = true,
            value = "select count(*) from m_order where user_id =:user")
    String countUserOrders (@Param("user") Long user);
}
