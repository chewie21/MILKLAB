package com.example.milk.repos;

import com.example.milk.domain.Product;
import com.example.milk.domain.ProductGroup;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductGroupRepo extends CrudRepository <ProductGroup, Long> {
    @Query(nativeQuery = true,
            value = "select count(*) from product_group_products p where products_id =:productId and product_group_id =:groupId")
     String productFromGroup (@Param("productId") Long productId, @Param("groupId") Long groupId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "DELETE FROM product_group_products WHERE products_id =:productId")
    void deleteProductFromGroup (@Param("productId") Long productId);
}
