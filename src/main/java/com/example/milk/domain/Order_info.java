package com.example.milk.domain;

import javax.persistence.*;

@Entity
public class Order_info {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long user_id;
    private String product_prod_name;

    public Order_info() {}

    public Order_info (Long id, Long user_id, String product_prod_name) {
        this.id = id;
        this.user_id = user_id;
        this.product_prod_name = product_prod_name;

    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getProduct_prod_name() {
        return product_prod_name;
    }

    public void setProduct_prod_name(String product_prod_name) {
        this.product_prod_name = product_prod_name;
    }
}
