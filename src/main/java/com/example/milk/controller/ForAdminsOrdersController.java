package com.example.milk.controller;

import com.example.milk.domain.Order;
import com.example.milk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/AdminOrders")
public class ForAdminsOrdersController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public String showOrders (Map<String, Object> model) {
        model.putIfAbsent("orders", orderService.findAll());
        return "AdminOrders";
    }
    @PostMapping("/activeOrders")
    public String showActiveOrders (RedirectAttributes attr) {
        attr.addFlashAttribute("orders", orderService.activeOrders(true));
        return "redirect:/AdminOrders";
    }
    @GetMapping("/delete/{order}")
    public String deleteOrder (@PathVariable Order order) {
        Long id = order.getId();
        orderService.deleteOrder(id);
        return "redirect:/AdminOrders";
    }
    @GetMapping("/active/{order}")
    public String activeOrder (@PathVariable Order order) {
        Long id = order.getId();
        orderService.activeOrder(id);
        return "redirect:/AdminOrders";
    }
    @GetMapping("/info/{order}")
    public String infoOrder (@PathVariable Order order, Model model) {
        Long id = order.getId();
        model.addAttribute("order", orderService.findById(id));
        return "AdminOrdersEdit";
    }
}
