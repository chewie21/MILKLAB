package com.example.milk.controller;

import com.example.milk.domain.Product;
import com.example.milk.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class ProductsController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/AdminProducts")
    public String ShowProduct (Map<String, Object> model) {
        Iterable<Product> products = productRepo.findAll();
        model.putIfAbsent("products", products);
        return "AdminProducts";
    }
    @PostMapping("/AdminProducts")
    public String AddProduct(Product product, Map<String, Object> model) {
        productRepo.save(product);
        Iterable<Product> products = productRepo.findAll();
        model.put("products", products);

        return "redirect:AdminProducts";
    }

    @PostMapping("/filter")
    public String filter (@RequestParam String findGroup, @RequestParam String findName, Map<String, Object> model, RedirectAttributes attr) {
        Iterable<Product> products;
        if (findGroup.equals("null")) {
            if (findName.isEmpty()) {
                return "redirect:/AdminProducts";
            }
            else
            products = productRepo.findByProdName(findName);
        } else if (findName.isEmpty()) {
            products = productRepo.findByProdGroup(findGroup);
        }
        else {
            products = productRepo.findByProdNameAndProdGroup(findName, findGroup);
        }
            attr.addFlashAttribute("products", products);
            return "redirect:/AdminProducts";
        }
}
