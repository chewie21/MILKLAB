package com.example.milk.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "m_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String time;
    private String address;
    private String date;
    private String orderCoast;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Order() {}

    public Order(User user,String orderCoast, String address, String time, String date) {
        this.user = user;
        this.orderCoast = orderCoast;
        this.address = address;
        this.time = time;
        this.date = date;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getOrderCoast() {
        return orderCoast;
    }

    public void setOrderCoast(String orderCoast) {
        this.orderCoast = orderCoast;
    }

    //User
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
