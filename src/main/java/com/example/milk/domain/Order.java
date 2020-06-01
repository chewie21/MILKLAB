package com.example.milk.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "m_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private Long orderCoast;
    private String address;
    private String time;
    private String date;
    private String comment;
    private boolean active;

    @Column
    @Enumerated(value = EnumType.STRING)
    private OrderStatusEnum status;

    public Order () {}

    public Order(User user, String address, String comment, String time, String date, Long orderCoast, OrderStatusEnum status) {
        this.user = user;
        this.address = address;
        this.comment = comment;
        this.time = time;
        this.date = date;
        this.orderCoast = orderCoast;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getOrderCoast() {
        return orderCoast;
    }

    public void setOrderCoast(Long orderCoast) {
        this.orderCoast = orderCoast;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
