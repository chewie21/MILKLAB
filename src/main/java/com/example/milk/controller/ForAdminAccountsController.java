package com.example.milk.controller;

import com.example.milk.domain.User;
import com.example.milk.service.OrderService;
import com.example.milk.service.ProductService;
import com.example.milk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/AdminAccounts")
public class ForAdminAccountsController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public String ShowUsers(Map<String, Object> model) {
        model.putIfAbsent("users", userService.findAll());
        model.put("countNotActiveOrders", orderService.countActiveOrders());
        model.put("countNotActiveUsers", userService.countNotActiveUsers());
        model.put("countStopProducts", productService.countStopProducts());
        model.putIfAbsent("title", null);
        return "AdminAccounts";
    }
    @GetMapping("{user}")
    public String EditAccount (@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        return "AdminAccountsEdit";
    }
    @PostMapping
    public String SaveAccount (
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String date,
            @RequestParam String password,
            @RequestParam String role,
            @RequestParam("id") User user,
            Model model) {
        if (!userService.checkEmail(user, email)) {
            model.addAttribute("user", user);
            model.addAttribute("error", "Такой Email уже зарегистрирован");
            return "AdminAccountsEdit";
        }
        if (!userService.checkUsername(user, username)){
            model.addAttribute("user", user);
            model.addAttribute("error", "Такой телефон уже зарегистрирован");
            return "AdminAccountsEdit";
        }
        if(!password.equals("")) {
            userService.savePassword(user, password);
        }
        userService.saveRole(user, role);
        userService.saveAccount(user, name, surname, date);
        return "redirect:/AdminAccounts";
    }
    @PostMapping("/filterAccounts")
    public String filterAccounts (@RequestParam Long id,
                                  @RequestParam String name,
                                  @RequestParam String surname,
                                  @RequestParam String username,
                                  @RequestParam String email,
                                  @RequestParam String date,
                                  @RequestParam String role,
                                  RedirectAttributes attr) {
        if (id != null){
            attr.addFlashAttribute("users", userService.findAllById(id));
            attr.addFlashAttribute("title", "id:" + id);
            return "redirect:/AdminAccounts";
        }
        else if (!name.equals("")) {
            attr.addFlashAttribute("users", userService.findAllByName(name));
            attr.addFlashAttribute("title", "Имя:" + name);
            return "redirect:/AdminAccounts";
        }
        else if (!surname.equals("")) {
            attr.addFlashAttribute("users", userService.findAllBySurname(surname));
            attr.addFlashAttribute("title", "Фамилия:" + surname);
            return "redirect:/AdminAccounts";
        }
        else if (!username.equals("+7 ")) {
            attr.addFlashAttribute("users", userService.findAllByUsername(username));
            attr.addFlashAttribute("title", "Телефон:" + username);
            return "redirect:/AdminAccounts";
        }
        else if (!email.equals("")) {
            attr.addFlashAttribute("users", userService.findAllByEmail(email));
            attr.addFlashAttribute("title", "Email:" + email);
            return "redirect:/AdminAccounts";
        }
        else if (!date.equals("")) {
            attr.addFlashAttribute("users", userService.findAllByDate(date));
            attr.addFlashAttribute("title", "Дата:" + date);
            return "redirect:/AdminAccounts";
        }
        else if (!role.equals("")) {
            attr.addFlashAttribute("users", userService.findAllByRole(role));
            attr.addFlashAttribute("title", role);
            return "redirect:/AdminAccounts";
        }
        else {
            return "redirect:/AdminAccounts";
        }
    }
    @PostMapping("/newAccount")
    public String newAccount (User user, Model model) {
        if (!userService.registration(user)) {
            model.addAttribute("message", "Такой пользователь уже существует");
        }
        return "redirect:/AdminAccounts";
    }
    @PostMapping("/blockAccount/{user}")
    public String blockAccount (@PathVariable User user) {
        userService.blockAccount(user);
        return "redirect:/AdminAccounts";
    }
    @PostMapping("/activeAccount/{user}")
    public String activeAccount (@PathVariable User user) {
        userService.activeAccount(user);
        return "redirect:/AdminAccounts";
    }
    @PostMapping("/findActive")
    public String findActiveAccounts (RedirectAttributes attr) {
        attr.addFlashAttribute("users", userService.findByActive());
        attr.addFlashAttribute("title", "Активные");
        return "redirect:/AdminAccounts";
    }
    @PostMapping("/findNotActive")
    public String findNotActiveAccounts (RedirectAttributes attr) {
        attr.addFlashAttribute("users", userService.findByNotActive());
        attr.addFlashAttribute("title", "Заблокированные");
        return "redirect:/AdminAccounts";
    }
}

