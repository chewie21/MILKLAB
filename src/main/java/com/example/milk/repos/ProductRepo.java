package com.example.milk.repos;

import com.example.milk.domain.Product;
import com.example.milk.domain.ProductGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository <Product, Long> {
   List<Product> findProductById(Long id);
   List<Product> findAllByProdCoast (Long prodCoast);
   List<Product> findAllByProdName (String prodName);
   List<Product> findAllById(Long id);
   List<Product> findAllByProductGroup(ProductGroup productGroup);
}
