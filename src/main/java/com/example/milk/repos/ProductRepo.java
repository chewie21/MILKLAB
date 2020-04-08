package com.example.milk.repos;

import com.example.milk.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository <Product, Integer> {
   List<Product> findByProdNameAndProdGroup (String prodName, String prodGroup);
   List<Product> findByProdGroup (String prodGroup);
   List<Product> findByProdName (String prodName);

}
