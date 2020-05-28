package com.example.milk.controller;

import com.example.milk.domain.Order;
import com.example.milk.domain.User;
import com.example.milk.service.OrderInfoService;
import com.example.milk.service.OrderService;
import com.example.milk.service.ProductService;
import com.example.milk.service.UserService;
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
    private OrderService orderService;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public String showOrders (Map<String, Object> model) {
        model.putIfAbsent("orders", orderService.findAll());
        model.put("countNotActiveOrders", orderService.countActiveOrders());
        model.put("countNotActiveUsers", userService.countNotActiveUsers());
        model.put("countStopProducts", productService.countStopProducts());
        model.putIfAbsent("title", null);
        return "AdminOrders";
    }
    @GetMapping("/delete/{order}")
    public String deleteOrder (@PathVariable Order order) {
        orderService.deleteOrder(order);
        return "redirect:/AdminOrders";
    }
    @GetMapping("/close/{order}")
    public String activeOrder (@PathVariable Order order) {
        Long id = order.getId();
        orderService.closeOrder(id);
        return "redirect:/AdminOrders";
    }
    @GetMapping("/orderInfo/{order}")
    public String showOrder (@PathVariable Order order,
                             Model model) {
        model.addAttribute("orderInfo", orderInfoService.findByOrderId(order.getId()));
        model.addAttribute("order", orderService.findById(order.getId()));
        return "orderInfo";
    }
    @PostMapping("/findActive")
    public String showActiveOrders (RedirectAttributes attr) {
        attr.addFlashAttribute("orders", orderService.findActiveOrders());
        attr.addFlashAttribute("title", "Активные");
        return "redirect:/AdminOrders";
    }
    @PostMapping("/findNotActive")
    public String showNotActiveOrders (RedirectAttributes attr) {
        attr.addFlashAttribute("orders", orderService.findNotActiveOrder());
        attr.addFlashAttribute("title", "Закрытые");
        return "redirect:/AdminOrders";
    }
    @PostMapping("/findDelivery")
    public String showDeliveryOrders (RedirectAttributes attr) {
        attr.addFlashAttribute("orders", orderService.findByDelivery());
        attr.addFlashAttribute("title", "Доставка");
        return "redirect:/AdminOrders";
    }
    @PostMapping("/findPickup")
    public String showPickupOrders (RedirectAttributes attr) {
        attr.addFlashAttribute("orders", orderService.findByPickup());
        attr.addFlashAttribute("title", "Самовывоз");
        return "redirect:/AdminOrders";
    }
    @PostMapping("/filterOrders")
    public String filterOrders (@RequestParam Long id,
                                @RequestParam String username,
                                @RequestParam String date,
                                @RequestParam Long orderCoast,
                                RedirectAttributes attr) throws ParseException {
        if (id != null) {
            attr.addFlashAttribute("orders", orderService.findAllById(id));
            attr.addFlashAttribute("title", "id:" + id);
            return "redirect:/AdminOrders";
        }
        else if (!username.equals("+7 ")) {
            attr.addFlashAttribute("orders", orderService.findAllByUsername(username));
            attr.addFlashAttribute("title", "Клиент:" + username);
            return "redirect:/AdminOrders";
        }
        else if (!date.equals("")) {
            SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyy-MM-dd", Locale.getDefault());
            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yy", Locale.getDefault());
            Date date1 = oldDateFormat.parse(date);
            String result = newDateFormat.format(date1);
            attr.addFlashAttribute("orders", orderService.findAllByDate(result));
            attr.addFlashAttribute("title", "Дата:" + result);
            return "redirect:/AdminOrders";
        }
        else if(orderCoast != 0) {
            attr.addFlashAttribute("orders", orderService.findByOrderCoast(orderCoast));
            attr.addFlashAttribute("title", "Цена:" + orderCoast);
            return "redirect:/AdminOrders";
        }
        else {
            return "redirect:/AdminOrders";
        }
    }
}
