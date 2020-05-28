package com.example.milk.service;

import com.example.milk.domain.Group;
import com.example.milk.domain.Product;
import com.example.milk.domain.ProductGroup;
import com.example.milk.repos.GroupRepo;
import com.example.milk.repos.ProductGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupService {

    @Autowired
    private ProductGroupRepo productGroupRepo;

    public ProductGroup findByGroupId (Long groupId) { return productGroupRepo.findByProductGroupId(groupId);}
    public ProductGroup findByProductId (Product product) {
        return productGroupRepo.findByProductId(product.getId());
    }
    public List<ProductGroup> findProductGroupByActive() {
        return productGroupRepo.findAllByActive();
    }
    public void saveProductInGroup (Group group, Product product) {
        ProductGroup productGroup = new ProductGroup(group, product);
        productGroupRepo.save(productGroup);
    }
    public String checkProductInGroup (Long groupId, Long productId) {
        return productGroupRepo.productFromGroup(productId, groupId);
    }
    public void deleteProductFromGroup (Long productId) {
        productGroupRepo.deleteProductFromGroup(productId);
    }
    public void deleteGroup (Long groupId) {
        productGroupRepo.deleteGroup(groupId);
    }
}
