package com.example.milk.controller;

import com.example.milk.domain.User;
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

    @GetMapping
    public String ShowUsers(Map<String, Object> model) {
        model.putIfAbsent("users", userService.findAll());
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
            @RequestParam("id") User user) {

        userService.saveAccount(user, name, surname, username, email, date, password);

        return "redirect:/AdminAccounts";
    }
    @PostMapping("/filterAccounts")
    public String filterAccounts (@RequestParam Long id,
                                  @RequestParam String name,
                                  @RequestParam String surname,
                                  @RequestParam String username,
                                  @RequestParam String email,
                                  @RequestParam String date, RedirectAttributes attr) {
        if (id != null){
            attr.addFlashAttribute("users", userService.findAllById(id));
            return "redirect:/AdminAccounts";
        }
        else if (!name.equals("")) {
            attr.addFlashAttribute("users", userService.findAllByName(name));
            return "redirect:/AdminAccounts";
        }
        else if (!surname.equals("")) {
            attr.addFlashAttribute("users", userService.findAllBySurname(surname));
            return "redirect:/AdminAccounts";
        }
        else if (!username.equals("+7 ")) {
            attr.addFlashAttribute("users", userService.findAllByUsername(username));
            return "redirect:/AdminAccounts";
        }
        else if (!email.equals("")) {
            attr.addFlashAttribute("users", userService.findAllByEmail(email));
            return "redirect:/AdminAccounts";
        }
        else if (!date.equals("")) {
            attr.addFlashAttribute("users", userService.findAllByDate(date));
            return "redirect:/AdminAccounts";
        }
        else {
            return "redirect:/AdminAccounts";
        }
    }
    @PostMapping("/newAccount")
    public String newAccount (User user, Model model) {
        User checkUser = userService.checkAccount(user);
        if (checkUser != null) {
            model.addAttribute("message", "Такой пользователь уже существует");
        }
        else {
            userService.newAccount(user);
        }
        return "redirect:/AdminAccounts";
    }
    @PostMapping("/deleteAccount/{user}")
    public String deleteAccount (@PathVariable User user) {
        userService.deleteAccount(user);
        return "redirect:/AdminAccounts";
    }
}

