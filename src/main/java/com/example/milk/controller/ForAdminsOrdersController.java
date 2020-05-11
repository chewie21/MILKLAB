package com.example.milk.controller;

import com.example.milk.domain.Order;
import com.example.milk.domain.User;
import com.example.milk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
    @GetMapping("/delete/{order}")
    public String deleteOrder (@PathVariable Order order) {
        Long id = order.getId();
        orderService.deleteOrder(id);
        return "redirect:/AdminOrders";
    }
    @GetMapping("/close/{order}")
    public String activeOrder (@PathVariable Order order) {
        Long id = order.getId();
        orderService.closeOrder(id);
        return "redirect:/AdminOrders";
    }
    @GetMapping("/info/{order}")
    public String infoOrder (@PathVariable Order order, Model model) {
        Long id = order.getId();
        model.addAttribute("order", orderService.findById(id));
        return "orderInfo";
    }
    @GetMapping("/orderInfo/{order}")
    public String showOrder (@PathVariable Order order,
                             Model model) {
        model.addAttribute("order", orderService.findById(order.getId()));
        return "orderInfo";
    }
    @PostMapping("/filterOrders")
    public String filterOrders (@RequestParam Long id,
                                @RequestParam String username,
                                @RequestParam String date,
                                @RequestParam boolean active,
                                RedirectAttributes attr) throws ParseException {
        SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyy-MM-dd", Locale.getDefault());
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yy", Locale.getDefault());
        Date date1 = oldDateFormat.parse(date);
        String result = newDateFormat.format(date1);

        if (id != null) {
            attr.addFlashAttribute("orders", orderService.findAllById(id));
            return "redirect:/AdminOrders";
        }
        else if (!username.equals("+7 ")) {
            attr.addFlashAttribute("orders", orderService.findAllByUsername(username));
            return "redirect:/AdminOrders";
        }
        else if (!result.equals("")) {
            attr.addFlashAttribute("orders", orderService.findAllByDate(result));
            return "redirect:/AdminOrders";
        }
        else {
            attr.addFlashAttribute("orders", orderService.activeOrders(active));
            return "redirect:/AdminOrders";
        }
    }
}
