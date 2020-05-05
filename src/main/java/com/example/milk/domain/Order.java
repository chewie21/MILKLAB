package com.example.milk.domain;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "m_order")
@SQLDelete(sql = "UPDATE m_order SET removed = true WHERE id=?", check = ResultCheckStyle.COUNT)
public class Order extends Audit {

    @Column
    private String address;

    @Column
    @Enumerated(value = EnumType.STRING)
    private OrderStatusEnum status;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Product> items;

    private Boolean removed = false;

    public Order() {}

    public Order(Long id, String address, OrderStatusEnum status, User user) {}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }
}
