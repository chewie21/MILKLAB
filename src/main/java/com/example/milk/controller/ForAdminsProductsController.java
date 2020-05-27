package com.example.milk.controller;

import com.example.milk.domain.Order;
import com.example.milk.domain.Product;
import com.example.milk.domain.Group;
import com.example.milk.domain.ProductStatusEnum;
import com.example.milk.service.OrderService;
import com.example.milk.service.GroupService;
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
    @Autowired
    private GroupService groupService;
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String ShowProduct(Map<String, Object> model) {
        model.putIfAbsent("products", productService.findAll());
        model.put("groups", groupService.findAllGroup());
        model.put("count", orderService.orderCount());
        model.put("stopProductCount", productService.stopProductCount());
        return "AdminProducts";
    }

    @GetMapping("{product}")
    public String EditProduct (@PathVariable Product product, Model model) {
        model.addAttribute("product", product);
        model.addAttribute("groups", groupService.findAllGroup());
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
            if (groupId != 0) {
                attr.addFlashAttribute("products", productService.findAllByGroup(groupId));
                return "redirect:/AdminProducts";
            }
            attr.addFlashAttribute("products", productService.findAllByGroupNot());
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
    public String deleteProduct (@PathVariable Product product) {
        productService.deleteProduct(product);
        return "redirect:/AdminProducts";
    }

    @PostMapping("/newGroup")
    public String saveGroup (@RequestParam String prodGroup) {
        groupService.SaveGroup(prodGroup);
        return "redirect:/AdminProducts";
    }

    @PostMapping("/deleteProductGroup/{productGroup}")
    public String deleteProductGroup (@PathVariable Group productGroup) {
        groupService.deleteGroup(productGroup);
        return "redirect:/AdminProducts";
    }

    @GetMapping("/close/{product}")
    public String closeProduct (@PathVariable Product product,
                                ProductStatusEnum status) {
        status = ProductStatusEnum.STOP;
        productService.saveProductStatus(product, status);
        return "redirect:/AdminProducts";
    }

    @GetMapping("/open/{product}")
    public String openProduct (@PathVariable Product product,
                                ProductStatusEnum status) {
        status = ProductStatusEnum.ACTIVE;
        productService.saveProductStatus(product, status);
        return "redirect:/AdminProducts";
    }

    @PostMapping("/findActive")
    public String showActiveProducts (RedirectAttributes attr) {
        attr.addFlashAttribute("products", productService.findAllByStatusActive());
        return "redirect:/AdminProducts";
    }

    @PostMapping("/findStop")
    public String showStopProducts (RedirectAttributes attr) {
        attr.addFlashAttribute("products", productService.findAllByStatusStop());
        return "redirect:/AdminProducts";
    }
}


