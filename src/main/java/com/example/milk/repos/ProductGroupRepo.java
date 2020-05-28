package com.example.milk.repos;

import com.example.milk.domain.ProductGroup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductGroupRepo extends CrudRepository <ProductGroup, Long> {
    List<ProductGroup> findAll();

    @Query(nativeQuery = true,
            value = "select count(*) from m_product_group_products where products_id =:productId and product_group_id =:groupId")
    String checkProductFromGroup (@Param("productId") Long productId, @Param("groupId") Long groupId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "DELETE FROM m_product_group_products WHERE products_id =:productId")
    void deleteProductFromProductGroup (@Param("productId") Long productId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "DELETE FROM m_product_group_products WHERE product_group_id =:groupId")
    void deleteGroupFromProductGroup (@Param("groupId") Long groupId);
}