package com.example.milk.repos;

import com.example.milk.domain.OrderInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderInfoRepo extends CrudRepository <OrderInfo, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "INSERT INTO m_order_products(prod_name,prod_coast,order_id) " +
                    "SELECT mp.prod_name,mp.prod_coast,mo.id " +
                    "FROM m_basket_products " +
                    "join m_product mp on m_basket_products.product_id = mp.id " +
                    "join m_basket mb on m_basket_products.basket_id = mb.id " +
                    "join m_order mo on mb.user_id = mo.user_id " +
                    "where m_basket_products.basket_id =:basketId AND mo.status ='ACTIVE'")
    void ProductToOrderInfo (@Param("basketId") Long basketId);

    @Transactional
    @Query(nativeQuery = true,
            value = "SELECT id FROM m_order_products where order_id =:orderId")
    List<Long> orderInfoId (@Param("orderId") Long orderId);

    List<OrderInfo> findByOrderId (Long orderId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "DELETE FROM m_order_products WHERE order_id =:orderId")
    void deleteFromOrderProductsByOrder (@Param("orderId") Long orderId);
}
