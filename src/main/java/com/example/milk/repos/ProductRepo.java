package com.example.milk.repos;

import com.example.milk.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends CrudRepository <Product, Long> {
   List<Product> findAllByProdCoast (Long prodCoast);
   List<Product> findAllByProdName (String prodName);
   List<Product> findAllById(Long id);

   @Query(nativeQuery = true,
           value = "select * from m_product join m_group_products mgp on m_product.id = mgp.product_id where mgp.group_id =:groupId")
   List<Product> findAllByProductGroup (@Param("groupId") Long groupId);

   @Query(nativeQuery = true,
           value = "select * from m_product left join m_group_products mgp on m_product.id = mgp.product_id where mgp.id is null")
   List<Product> findAllByProductNotGroup ();

   @Query(nativeQuery = true,
           value = "select count(*) from m_product where status='STOP'")
   String stopProductCount();

   @Query(nativeQuery = true,
           value = "select * from m_product where status='ACTIVE'")
   List<Product> findAllByStatusActive();

   @Query(nativeQuery = true,
           value = "select * from m_product where status='STOP'")
   List<Product> findAllByStatusStop();

}
