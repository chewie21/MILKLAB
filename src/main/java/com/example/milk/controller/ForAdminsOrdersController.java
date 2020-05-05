package com.example.milk.controller;

import com.example.milk.domain.Order;
import com.example.milk.domain.Product;
import com.example.milk.domain.User;
import com.example.milk.repos.OrderRepo;
import com.example.milk.service.OrderService;
import com.example.milk.service.ProductService;
import com.example.milk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/AdminOrders")
public class ForAdminsOrdersController {
}
