package com.example.milk.domain;

import javax.persistence.*;

@Entity
@Table (name = "milk_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String prodGroup;
    private String prodName;
    private Long prodCoast;

    public Product() {}

    public Product (String prodName, Long prodCoast, String prodGroup) {
        this.prodName = prodName;
        this.prodGroup = prodGroup;
        this.prodCoast = prodCoast;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProdGroup() {
        return prodGroup;
    }

    public void setProdGroup(String prodGroup) {
        this.prodGroup = prodGroup;
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
