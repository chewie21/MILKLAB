package com.example.milk.controller;

import com.example.milk.domain.User;
import com.example.milk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

    @Controller
    public class RegistrationController {

        @Autowired
        private UserService userService;

        @GetMapping("/registration")
        public String registration() {
            return "/registration";
        }

        @PostMapping("/registration")
        public String addUser(User user, Model model) {

            User checkUser = userService.checkAccount(user);
            if (checkUser != null) {
                model.addAttribute("message", "Такой пользователь уже существует");
                return "registration";
            }
            else {
                userService.newAccount(user);
            }
            return "redirect:/login";
        }
    }
