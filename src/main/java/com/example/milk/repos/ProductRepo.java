package com.example.milk.repos;

import com.example.milk.domain.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.JoinColumn;
import java.util.List;

public interface ProductRepo extends CrudRepository <Product, Long> {
   List<Product> findAllByProdCoast (Long prodCoast);
   List<Product> findAllByProdName (String prodName);
   List<Product> findAllById(Long id);
   List<Product> findAllByProductGroupId (Long productGroupId);

   @Query(nativeQuery = true,
           value = "select * from m_product  where m_product.product_group_id is null")
   List<Product> findAllByProductNotGroup ();

   @Query(nativeQuery = true,
           value = "select count(*) from m_product where status='STOP'")
   String countStopProducts();

   @Query(nativeQuery = true,
           value = "select * from m_product where status='ACTIVE'")
   List<Product> findAllByStatusActive();

   @Query(nativeQuery = true,
           value = "select * from m_product where status='STOP'")
   List<Product> findAllByStatusStop();

   @Transactional
   @Modifying
   @Query(nativeQuery = true,
           value = "update m_product SET m_product.product_group_id = NULL WHERE product_group_id =:groupId")
   void deleteGroupFromProduct(@Param("groupId")Long groupId);
}
