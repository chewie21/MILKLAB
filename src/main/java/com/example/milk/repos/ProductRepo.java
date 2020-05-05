package com.example.milk.repos;

import com.example.milk.domain.Order;
import com.example.milk.domain.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends CrudRepository <Product, Long> {
   List<Product> findProductById(Long id);
   List<Product> findAllByProdCoast (Long prodCoast);
   List<Product> findAllByProdGroup (String prodGroup);
   List<Product> findAllByProdName (String prodName);
   List<Product> findAllById(Long id);


}
