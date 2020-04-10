package com.example.milk.controller;

import com.example.milk.domain.Product;
import com.example.milk.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MenuController {

    @Autowired
    public ProductRepo productRepo;

    @GetMapping("/menu")
    public String ShowMenu (Map<String, Object> model) {
        Iterable<Product> products = productRepo.findAll();
        model.put("products", products);
        return "menu";
    }
}
