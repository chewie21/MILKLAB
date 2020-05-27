package com.example.milk.domain;

import javax.persistence.*;

@Entity
@Table(name = "m_order_products")
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prodName;
    private Long prodCoast;

    @ManyToOne
    @JoinColumn(name = "order_id")
    public Order order;

    public OrderInfo() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Long getProdCoast() {
        return prodCoast;
    }

    public void setProdCoast(Long prodCoast) {
        this.prodCoast = prodCoast;
    }
}
