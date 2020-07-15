package com.example.milk.controller;

import com.example.milk.domain.User;
import com.example.milk.service.CarouselService;
import com.example.milk.service.InfoService;
import com.example.milk.service.ReviewService;
import com.example.milk.util.Messenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class InformationController {

    @Autowired
    private InfoService infoService;
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public String greeting (@AuthenticationPrincipal User user,
                            Map<String, Object> model) {
        model.put("user", user);
        if (user != null) {
            model.put("count", infoService.countAdminActivity());
        }
        return "main";
    }
    @PostMapping("/main")
    public String message (@AuthenticationPrincipal User user,
                           @RequestParam String message, String recipientNumber,
                           Map<String, Object> model) throws Exception {
        model.put("user", user);
        if (user != null) {
            model.put("count", infoService.countAdminActivity());
        }
        Messenger.sendMessage(recipientNumber,message);
        return "main";
    }

    @GetMapping("/information")
    public String information(@AuthenticationPrincipal User user,
                              Map<String, Object> model) {
        model.put("reviewsFirst", reviewService.findAllByGoodRatingFirst());
        model.put("reviewsSecond", reviewService.findAllByGoodRatingSecond());
        model.put("user", user);
        if (user != null) {
            model.put("count", infoService.countAdminActivity());
        }
        return "information";
    }

    @GetMapping("/contacts")
    public String contacts(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("carousels", carouselService.findAllByGroupContacts());
        model.addAttribute("user", user);
        if (user != null) {
            model.addAttribute("count", infoService.countAdminActivity());
        }
        return "contacts";
    }
}
