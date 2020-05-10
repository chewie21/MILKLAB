package com.example.milk.controller;

import com.example.milk.domain.Product;
import com.example.milk.service.ProductGroupService;
import com.example.milk.service.ProductService;
import com.example.milk.service.CarouselService;
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
    @Autowired
    private ProductGroupService productGroupService;

    @GetMapping
    public String ShowProduct(Map<String, Object> model) {
        model.putIfAbsent("products", productService.findAll());
        model.put("groups", productGroupService.findAllGroup());
        return "AdminProducts";
    }
    @GetMapping("{product}")
    public String EditProduct (@PathVariable Product product, Model model) {
        model.addAttribute("product", product);
        model.addAttribute("groups", productGroupService.findAllGroup());
        return "AdminProductsEdit";
    }
    @PostMapping
    public String SaveProduct (
            @RequestParam String prodName,
            @RequestParam String prodInfo,
            @RequestParam Long groupId,
            @RequestParam Long prodCoast,
            @RequestParam("prodImg") MultipartFile file,
            @RequestParam("id") Product product) throws IOException {
        productService.saveProduct(product, groupId, prodName, prodInfo, prodCoast, file);
        return "redirect:/AdminProducts";
    }


    @PostMapping("/newProduct")
    public String NewProduct(@RequestParam String prodName,
                             @RequestParam String prodInfo,
                             @RequestParam Long groupId,
                             @RequestParam Long prodCoast,
                             @RequestParam("prodImg") MultipartFile file) throws IOException {
        productService.newProduct(groupId, prodName, prodCoast, prodInfo, file);
        return "redirect:/AdminProducts";
    }

    @PostMapping("/filterProducts")
    public String filterProducts(@RequestParam Long id,
                                 @RequestParam String prodName,
                                 @RequestParam Long groupId,
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
        else if (groupId != null) {
            attr.addFlashAttribute("products", productService.findAllByGroup(groupId));
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
    @PostMapping("/newGroup")
    public String saveGroup (@RequestParam String prodGroup) {
        productGroupService.SaveGroup(prodGroup);
        return "redirect:/AdminProducts";
    }
}


