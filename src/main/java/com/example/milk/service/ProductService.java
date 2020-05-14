package com.example.milk.service;

import com.example.milk.domain.Product;
import com.example.milk.domain.ProductGroup;
import com.example.milk.repos.ProductGroupRepo;
import com.example.milk.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductGroupService productGroupService;

    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    public void deleteProduct(Product product) {
        productGroupService.deleteProductFromGroup(product.getId());
        productRepo.delete(product);
    }

    public void newProduct(Long groupId, String prodName, Long prodCoast, String prodInfo, MultipartFile file) throws IOException {
        Product product = new Product(prodName, prodInfo, prodCoast);
        if (checkImg(file)) {
            product.setProdImg(file.getOriginalFilename());
        }
        product.setProductGroup(productGroupService.findById(groupId));
        productRepo.save(product);
        productGroupService.saveProductInGroup(groupId, product.getId());
    }

    public void saveProduct(Product product,Long groupId, String prodName, String prodInfo, Long prodCoast, MultipartFile file) throws IOException {
        product.setProdName(prodName);
        product.setProdInfo(prodInfo);
        product.setProdCoast(prodCoast);
        product.setProductGroup(productGroupService.findById(groupId));
        if (!productGroupService.checkProductInGroup(groupId, product.getId()).equals("1")) {
            productGroupService.deleteProductFromGroup(product.getId());
            productGroupService.saveProductInGroup(groupId, product.getId());
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
        ProductGroup productGroup = productGroupService.findById(groupId);
        return productRepo.findAllByProductGroup(productGroup);
    }
}
