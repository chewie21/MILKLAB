package com.example.milk.service;

import com.example.milk.domain.Group;
import com.example.milk.domain.Product;
import com.example.milk.domain.ProductStatusEnum;
import com.example.milk.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductGroupService productGroupService;
    @Autowired
    private GroupService groupService;

    public String countStopProducts() {
        return productRepo.countStopProducts().equals("0") ? null : productRepo.countStopProducts();
    }
    public List<Product> findAllByStatusActive() {
        return productRepo.findAllByStatusActive();
    }
    public List<Product> findAllByStatusStop() {
        return productRepo.findAllByStatusStop();
    }

    //Edit
    public void saveProductStatus(Product product, ProductStatusEnum status) {
        product.setStatus(status);
        productRepo.save(product);
    }
    public void newProduct(Long groupId, String prodName, Long prodCoast, String prodInfo, MultipartFile file) throws IOException {
        Product product = new Product(prodName, prodInfo, prodCoast, ProductStatusEnum.ACTIVE);
        if (checkImg(file)) {
            product.setProdImg(file.getOriginalFilename());
        }
        productRepo.save(product);
        if (groupId != null) {
            Group group = groupService.findById(groupId);
            productGroupService.saveProductInGroup(group, product);
        }
    }
    public void saveProduct(Product product, Long groupId, String prodName, String prodInfo, Long prodCoast, MultipartFile file) throws IOException {
        product.setProdName(prodName);
        product.setProdInfo(prodInfo);
        product.setProdCoast(prodCoast);
        if (!productGroupService.checkProductInGroup(groupId, product.getId()).equals("1")) {
            productGroupService.deleteProductFromGroup(product.getId());
            Group group = groupService.findById(groupId);
            productGroupService.saveProductInGroup(group, product);
        }
        if (checkImg(file)) {
            product.setProdImg(file.getOriginalFilename());
        }
        productRepo.save(product);
    }
    public boolean checkImg(MultipartFile file) throws IOException {
        if (!file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            file.transferTo(new File(uploadPath + "/" + file.getOriginalFilename()));
            return true;
        } else return false;
    }

    //Find
    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }
    public List<Product> findAllById(Long id) {
        return productRepo.findAllById(id);
    }
    public List<Product> findAllByName(String prodName) {
        return productRepo.findAllByProdName(prodName);
    }
    public List<Product> findAllByCoast(Long prodCoast) {
        return productRepo.findAllByProdCoast(prodCoast);
    }
    public List<Product> findAllByGroup(Long groupId) {
        return productRepo.findAllByProductGroup(groupId);
    }
    public List<Product> findAllByGroupNot() {
        return productRepo.findAllByProductNotGroup();
    }
    public void deleteProduct(Product product) {
        productGroupService.deleteProductFromGroup(product.getId());
        productRepo.delete(product);
    }
}
