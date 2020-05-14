package com.example.milk.repos;

import com.example.milk.domain.BasketInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

public interface BasketInfoRepo extends CrudRepository <BasketInfo, Long> {

    BasketInfo findByBasketId (Long basketId);
    List<BasketInfo> findAllByBasketId (Long basketId);

    @Query(nativeQuery = true,
            value = "SELECT m.product_id FROM m_basket_products m where basket_id =:basket")
    List<Long> findProductId (@Param("basket") Long basketId);

    @Query(nativeQuery = true,
            value = "SELECT SUM(mp.prod_coast) FROM m_basket_products mbp JOIN m_basket mb on mbp.basket_id = mb.id JOIN m_product mp on mbp.product_id = mp.id  WHERE user_id =:user")
    String orderCoast (@Param("user") Long user);

    @Query(nativeQuery = true,
            value = "SELECT count(mbp.product_id) FROM m_basket_products mbp JOIN m_basket mb on mbp.basket_id = mb.id where user_id =:user")
    String countProducts (@Param("user") Long user);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "DELETE FROM m_basket_products WHERE product_id =:pid AND basket_id =:bid LIMIT 1")
    void deleteFromBasket (@Param("pid") Long productId, @Param("bid") Long basketId);
}
