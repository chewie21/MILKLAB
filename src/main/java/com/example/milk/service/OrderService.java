package com.example.milk.service;

import com.example.milk.domain.*;
import com.example.milk.repos.OrderInfoRepo;
import com.example.milk.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private ReviewService reviewService;

    public Order lastOrder (Long userId) {
        return orderRepo.lastOrder(userId);
    }
    public String countUserActiveOrders (Long userId) {
        return orderRepo.countUserActiveOrders(userId).equals("0") ? null : orderRepo.countUserActiveOrders(userId);
    }
    public String countUserNotActiveOrders (Long userId) {
        return orderRepo.countUserNotActiveOrders(userId).equals("0") ? null : orderRepo.countUserNotActiveOrders(userId);
    }
    public String countUserOrders (Long userId) {
        return orderRepo.countUserOrders(userId).equals("0") ? null : orderRepo.countUserOrders(userId);
    }
    public String countActiveOrders () {
        return orderRepo.countActiveOrders().equals("0") ? null : orderRepo.countActiveOrders();
    }
    public String dateFormat () {
        Date nowDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        return dateFormat.format(nowDate);
    }
    public String timeFormat () {
        Date nowDate = new Date();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(nowDate);
    }

    //Edit
    public void saveOrder (Basket basket, User user, String address, String comment, String time, String date, Long orderCoast, OrderStatusEnum orderStatusEnum) {
        Order order = findOrder(user, address, comment, time, date, orderCoast);
        order.setActive(true);
        orderInfoService.newOrderInfo(basket);
        order.setStatus(orderStatusEnum);
        orderRepo.save(order);
        reviewService.closeDiscount(user);
    }
    public Order newOrder (User user, String address, String comment, String time, String date, Long orderCoast) {
        Order order = new Order (user, address, comment, time, date, orderCoast, OrderStatusEnum.ACTIVE);
        orderRepo.save(order);
        return order;
    }
    public void deleteOrder (Order order) {
        orderInfoService.deleteFromOrderProductsByOrder(order);
        orderRepo.delete(order);
    }
    public void closeOrder (Long id) {
        Order order = orderRepo.findById(id).get();
        order.setActive(false);
        orderRepo.save(order);
    }

    //Find
    public Iterable<Order> findAll() {
        return orderRepo.findAll();
    }
    public Order findById (Long id) {
        return orderRepo.findById(id).get();
    }
    public Order findOrder (User user, String address, String comment, String time, String date, Long orderCoast) {
        Order order = orderRepo.findByUserIdAndStatus(user.getId(), OrderStatusEnum.ACTIVE);
        return order == null ? newOrder(user, address, comment, time, date, orderCoast) : order;
    }
    public List<Order> findAllById(Long id) {
        return orderRepo.findAllById(id);
    }
    public List<Order> findAllByUsername (String username) {
        return orderRepo.findAllByUserUsername(username);
    }
    public List<Order> findAllByDate (String date) {
        return orderRepo.findAllByDate(date);
    }
    public List<Order> findByTrueActive (User user, boolean active) {
        return orderRepo.findAllByUserIdAndActive(user.getId(), true);
    }
    public List<Order> findByFalseActive (User user, boolean active) {
        return orderRepo.findAllByUserIdAndActive(user.getId(), false);
    }
    public List<Order> findActiveOrders () {
        return orderRepo.findAllByActive(true);
    }
    public List<Order> findNotActiveOrder () {
        return orderRepo.findAllByActive(false);
    }
    public List<Order> findByOrderCoast (Long orderCoast) {
        return orderRepo.findAllByOrderCoast(orderCoast);
    }
    public List<Order> findByDelivery () {
        return orderRepo.findAllByStatus(OrderStatusEnum.DELIVERY);
    }
    public List<Order> findByPickup () {
        return orderRepo.findAllByStatus(OrderStatusEnum.PICKUP);
    }

}

