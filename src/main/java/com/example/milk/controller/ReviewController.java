package com.example.milk.controller;

import com.example.milk.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public String newReview () {
        return "review";
    }

    @PostMapping
    public String saveReview (@RequestParam String name,
                              @RequestParam String username,
                              @RequestParam Long rating,
                              @RequestParam String text,
                              @RequestParam String shortText,
                              Map<String, String> model) {
        reviewService.newReview(username, name, rating, text, shortText);
        if (rating < 4) {
            model.put("text", "Очень жаль, что тебе не понравилось :(" +
                    " Мы обещаем исправить все ошибки, чтобы твой следующий визит прошел безупречно!");
        }
        else {
            model.put("text", "Спасибо за отзыв! Очень рады, что тебе понравилость! Ждем тебя снова!");
        }
        return "reviewSuccess";
    }
}
