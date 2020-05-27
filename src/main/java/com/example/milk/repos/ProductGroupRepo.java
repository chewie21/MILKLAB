package com.example.milk.repos;

import com.example.milk.domain.ProductGroup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductGroupRepo extends CrudRepository <ProductGroup, Long> {

    @Query(nativeQuery = true,
            value = "select * from m_group_products join m_product mp on m_group_products.product_id = mp.id where status='ACTIVE'")
    List<ProductGroup> findAllByActive();

    @Query(nativeQuery = true,
            value = "select count(*) from m_group_products where product_id =:productId and group_id =:groupId")
    String productFromGroup (@Param("productId") Long productId, @Param("groupId") Long groupId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "DELETE FROM m_group_products WHERE product_id =:productId")
    void deleteProductFromGroup (@Param("productId") Long productId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "DELETE FROM m_group_products WHERE group_id =:groupId")
    void deleteGroup(Long groupId);
}
