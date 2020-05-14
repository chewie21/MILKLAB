package com.example.milk.service;

import com.example.milk.domain.Product;
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
    public void saveProductInGroup (Long groupId, Long productId) {
        ProductGroup productGroup = productGroupRepo.findById(groupId).get();
        productGroup.getProducts().add(new Product(productId));
        productGroupRepo.save(productGroup);
    }
    public ProductGroup findById (Long groupId) {
        return productGroupRepo.findById(groupId).get();
    }
    public String checkProductInGroup (Long groupId, Long productId) {
        return productGroupRepo.productFromGroup(productId, groupId);
    }
    public void deleteProductFromGroup (Long productId) {
        productGroupRepo.deleteProductFromGroup(productId);
    }
}
