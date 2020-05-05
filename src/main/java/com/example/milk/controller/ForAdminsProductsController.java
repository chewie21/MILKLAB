package com.example.milk.controller;

import com.example.milk.domain.Product;
import com.example.milk.domain.User;
import com.example.milk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/AdminProducts")
public class ForAdminsProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String ShowProduct(Map<String, Object> model) {
        model.putIfAbsent("products", productService.findAll());
        return "AdminProducts";
    }
    @GetMapping("{product}")
    public String EditProduct (@PathVariable Product product, Model model) {
        model.addAttribute("product", product);
        return "AdminProductsEdit";
    }
    @PostMapping
    public String SaveProduct (
            @RequestParam String prodName,
            @RequestParam String prodInfo,
            @RequestParam String prodGroup,
            @RequestParam Long prodCoast,
            @RequestParam("prodImg") MultipartFile file,
            @RequestParam("id") Product product) throws IOException {
        productService.saveProduct(product, prodName, prodInfo, prodCoast, file);
        return "redirect:/AdminProducts";
    }


    @PostMapping("/newProduct")
    public String NewProduct(@RequestParam String prodName,
                             @RequestParam String prodInfo,
                             @RequestParam String prodGroup,
                             @RequestParam Long prodCoast,
                             @RequestParam("prodImg") MultipartFile file) throws IOException {
        productService.newProduct(prodName, prodCoast, prodInfo, file);
        return "redirect:/AdminProducts";
    }

    @PostMapping("/filterProducts")
    public String filterProducts(@RequestParam Long id,
                                 @RequestParam String prodName,
                                 @RequestParam String prodGroup,
                                 @RequestParam Long prodCoast,
                                 RedirectAttributes attr) {
        if (id != null) {
            attr.addFlashAttribute("products", productService.findAllById(id));
            return "redirect:/AdminProducts";
        }
        else if (!prodName.equals("")) {
            attr.addFlashAttribute("products", productService.findAllByName(prodName));
            return "redirect:/AdminProducts";
        }
        else if (!prodGroup.equals("")) {
            attr.addFlashAttribute("products", productService.findAllByGroup(prodGroup));
            return "redirect:/AdminProducts";
        }
        else if (prodCoast != null) {
            attr.addFlashAttribute("products", productService.findAllByCoast(prodCoast));
            return "redirect:/AdminProducts";
        }
        else {
            return "redirect:/AdminProducts";
        }
    }
    @PostMapping("/deleteProduct/{product}")
    public String deleteAccount (@PathVariable Product product) {
        productService.deleteProduct(product);
        return "redirect:/AdminProducts";
    }
}


