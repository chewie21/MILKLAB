package com.example.milk.controller;

import com.example.milk.domain.Carousel;
import com.example.milk.service.CarouselService;
import com.example.milk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/AdminCarousels")
public class ForAdminCarouselsController {

    @Autowired
    private CarouselService carouselService;
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String showCarousel(Map<String, Object> model) {
        model.put("carousels", carouselService.findAll());
        model.put("count", orderService.orderCount());
        return "AdminCarousels";
    }

    @GetMapping("{carousel}")
    public String carouselEdit (@PathVariable Carousel carousel, Model model) {
        model.addAttribute("carousel", carousel);
        return "AdminCarouselsEdit";
    }
    @PostMapping
    public String saveCarousel (@RequestParam String carouselInfo,
                                @RequestParam("carouselImg") MultipartFile file,
                                @RequestParam("id") Carousel carousel) throws IOException {
        carouselService.saveCarousel(carousel, carouselInfo, file);
        return "redirect:/AdminCarousels";
    }

    @PostMapping("/newCarousel")
    public String newCarousel (@RequestParam String carouselInfo,
                               @RequestParam("carouselImg") MultipartFile file) throws IOException {
        carouselService.newCarousel(carouselInfo,file);
        return "redirect:/AdminCarousels";
    }
    @PostMapping("deleteCarousel/{carousel}")
    public  String deleteCarousel (@PathVariable Carousel carousel) {
        carouselService.deleteCarousel(carousel);
        return "redirect:/AdminCarousels";
    }
}
