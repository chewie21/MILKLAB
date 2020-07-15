package com.example.milk.controller;

import com.example.milk.domain.Carousel;
import com.example.milk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public String showCarousel(Map<String, Object> model) {
        model.putIfAbsent("carousels", carouselService.findAll());
        model.put("countNotActiveOrders", orderService.countActiveOrders());
        model.put("countNotActiveUsers", userService.countNotActiveUsers());
        model.put("countStopProducts", productService.countStopProducts());
        model.put("countNewReviews", reviewService.countNewReviews());
        model.putIfAbsent("title", null);
        return "AdminCarousels";
    }

    @GetMapping("{carousel}")
    public String carouselEdit (@PathVariable Carousel carousel, Model model) {
        model.addAttribute("carousel", carousel);
        return "AdminCarouselsEdit";
    }
    @PostMapping
    public String saveCarousel (@RequestParam String carouselInfo,
                                @RequestParam Long carouselGroup,
                                @RequestParam("carouselImg") MultipartFile file,
                                @RequestParam("id") Carousel carousel) throws IOException {
        carouselService.saveCarousel(carousel, carouselGroup, carouselInfo, file);
        return "redirect:/AdminCarousels";
    }

    @PostMapping("/newCarousel")
    public String newCarousel (@RequestParam String carouselInfo,
                               @RequestParam Long carouselGroup,
                               @RequestParam("carouselImg") MultipartFile file) throws IOException {
        carouselService.newCarousel(carouselGroup, carouselInfo,file);
        return "redirect:/AdminCarousels";
    }
    @PostMapping("deleteCarousel/{carousel}")
    public String deleteCarousel (@PathVariable Carousel carousel) {
        carouselService.deleteCarousel(carousel);
        return "redirect:/AdminCarousels";
    }
    @PostMapping("/findMenu")
    public String findMenu (RedirectAttributes attr) {
        attr.addFlashAttribute("carousels", carouselService.findAllByGroupMenu());
        attr.addFlashAttribute("title", "Картинки в основном меню");
        return "redirect:/AdminCarousels";
    }
    @PostMapping("/findContacts")
    public String findContacts (RedirectAttributes attr) {
        attr.addFlashAttribute("carousels", carouselService.findAllByGroupContacts());
        attr.addFlashAttribute("title", "Картинки в контактах");
        return "redirect:/AdminCarousels";
    }
}
