package com.example.milk.service;

import com.example.milk.domain.Product;
import com.example.milk.domain.ProductGroup;
import com.example.milk.repos.ProductGroupRepo;
import com.example.milk.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupService {

    @Autowired
    private ProductGroupRepo productGroupRepo;
    @Autowired
    private ProductRepo productRepo;


    //Check
    public boolean checkProductInGroup (Long groupId, Long productId) {
        return productGroupRepo.checkProductFromGroup(productId, groupId).equals("1");
    }
    //Find
    public List<ProductGroup> findAllGroup() {
        return productGroupRepo.findAll();
    }
    public ProductGroup findById (Long groupId) {
        return productGroupRepo.findById(groupId).get();
    }

    //Edit
    public void SaveGroup(String prodGroup) {
        ProductGroup productGroup = new ProductGroup(prodGroup);
        productGroupRepo.save(productGroup);
    }
    public void SaveProductInGroup (Long groupId, Long productId) {
        Product product = productRepo.findById(productId).get();
        product.setProductGroup(productGroupRepo.findById(groupId).get());
        ProductGroup productGroup = productGroupRepo.findById(groupId).get();
        productGroup.getProducts().add(new Product(productId));
        productGroupRepo.save(productGroup);
    }
    public void deleteGroup (ProductGroup productGroup) {
        productGroupRepo.deleteGroupFromProductGroup(productGroup.getId());
        productRepo.deleteGroupFromProduct(productGroup.getId());
        productGroupRepo.delete(productGroup);
    }
    public void deleteProductFromGroup (Long productId) {
        productGroupRepo.deleteProductFromProductGroup(productId);
        Product product = productRepo.findById(productId).get();
        product.setProductGroup(null);
        productRepo.save(product);
    }
}
