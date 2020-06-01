package com.example.milk.controller;

import com.example.milk.domain.Order;
import com.example.milk.domain.User;
import com.example.milk.repos.UserRepo;
import com.example.milk.service.InfoService;
import com.example.milk.service.OrderInfoService;
import com.example.milk.service.OrderService;
import com.example.milk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private InfoService infoService;

    @GetMapping
    public String ShowAccount (@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("active", orderService.findByTrueActive(user, true) );
        model.addAttribute("notActive", orderService.findByFalseActive(user, false));
        model.addAttribute("lastOrder", orderService.lastOrder(user.getId()));
        model.addAttribute("countUserActiveOrders", orderService.countUserActiveOrders(user.getId()));
        model.addAttribute("countUserNotActiveOrders", orderService.countUserNotActiveOrders(user.getId()));
        model.addAttribute("userOrders", orderService.countUserOrders(user.getId()));
        model.addAttribute("count", infoService.countAdminActivity());
        return "account";
    }
    @GetMapping("edit")
    public String EditAccount (@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "accountEdit";
    }
    @PostMapping("edit")
    public String SaveAccount (@AuthenticationPrincipal User user,
                               @RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String date,
                               Model model) {
        if (!userService.checkEmail(user, email)) {
            model.addAttribute("user", user);
            model.addAttribute("error", "Такой Email уже зарегистрирован");
            return "accountEdit";
        }
        if (!userService.checkUsername(user, username)){
            model.addAttribute("user", user);
            model.addAttribute("error", "Такой телефон уже зарегистрирован");
            return "accountEdit";
        }
        userService.saveAccount(user, name, surname, date);
        return "redirect:/account";
    }
    @GetMapping("/orderInfo/{order}")
    public String showOrder (@PathVariable Order order,
                             Model model) {
        model.addAttribute("orderInfo", orderInfoService.findByOrderId(order.getId()));
        model.addAttribute("order", orderService.findById(order.getId()));
        return "orderInfo";
    }


}

