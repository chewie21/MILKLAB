package com.example.milk.service;

import com.example.milk.domain.ProductGroup;
import com.example.milk.repos.ProductGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupService {

    @Autowired
    private ProductGroupRepo productGroupRepo;

    public Iterable<ProductGroup> findAllGroup() {return productGroupRepo.findAll();}
    public void SaveGroup(String prodGroup) {
        ProductGroup productGroup = new ProductGroup(prodGroup);
        productGroupRepo.save(productGroup);
    }
}
