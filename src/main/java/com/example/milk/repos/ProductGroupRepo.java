package com.example.milk.repos;

import com.example.milk.domain.ProductGroup;
import org.springframework.data.repository.CrudRepository;

public interface ProductGroupRepo extends CrudRepository <ProductGroup, Long> {
}
