package com.example.milk.repos;

import com.example.milk.domain.Order_info;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderInfoRepo extends CrudRepository<Order_info, Long> {

    @Query(nativeQuery = true, value = "SELECT m_order.user_id, m_basket.product_prod_name FROM m_order JOIN m_basket ON m_basket.user_id = m_order.user_id where m_order.user_id = :user")
    List<Order_info> findByUserId (@Param("user") Long user);
}

