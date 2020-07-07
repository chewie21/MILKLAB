package com.example.milk.controller;

import com.example.milk.domain.Review;
import com.example.milk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/AdminReviews")
public class ForAdminsReviewsController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public String showReviews (Map<String, Object> model) {
        model.putIfAbsent("reviews", reviewService.findAll());
        model.putIfAbsent("title", null);
        model.put("countNotActiveOrders", orderService.countActiveOrders());
        model.put("countNotActiveUsers", userService.countNotActiveUsers());
        model.put("countStopProducts", productService.countStopProducts());
        model.put("countNewReviews", reviewService.countNewReviews());
        return "AdminReviews";
    }

    @GetMapping("{review}")
    public String showReview (@PathVariable Review review,
                              Map <String, Object> model) {
        model.put("review", review);
        reviewService.closeReview(review);
        return "AdminReviewsInfo";
    }

    @PostMapping("/deleteReview/{review}")
    public String deleteReview (@PathVariable Review review) {
        reviewService.deleteReview(review);
        return "redirect:/AdminReviews";
    }

    @PostMapping("/findGood")
    public String goodReviews (RedirectAttributes attr) {
        attr.addFlashAttribute("reviews", reviewService.findByGood());
        attr.addFlashAttribute("title", "Положительные");
        return "redirect:/AdminReviews";
    }

    @PostMapping("/findNormal")
    public String normalReviews (RedirectAttributes attr) {
        attr.addFlashAttribute("reviews", reviewService.findByNormal());
        attr.addFlashAttribute("title", "Нейтральные");
        return "redirect:/AdminReviews";
    }

    @PostMapping("/findBad")
    public String badReviews (RedirectAttributes attr) {
        attr.addFlashAttribute("reviews", reviewService.findByBad());
        attr.addFlashAttribute("title", "Отрицательные");
        return "redirect:/AdminReviews";
    }

    @PostMapping("/new")
    public String newReviews (RedirectAttributes attr) {
        attr.addFlashAttribute("reviews", reviewService.findNewReview());
        attr.addFlashAttribute("title", "Новые");
        return "redirect:/AdminReviews";
    }
}
