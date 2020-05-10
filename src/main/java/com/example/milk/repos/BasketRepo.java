package com.example.milk.repos;

import com.example.milk.domain.Basket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketRepo extends CrudRepository<Basket, Long> {
    List<Basket> findAllByUserId (Long id);
    Basket findByUserId (Long id);

    @Query(nativeQuery = true,
            value = "SELECT SUM(mp.prod_coast) FROM m_basket b JOIN m_basket_products mbp on b.id = mbp.basket_id JOIN m_product mp on mbp.products_id = mp.id WHERE b.user_id =:user")
    String orderCoast (@Param("user") Long user);


}
