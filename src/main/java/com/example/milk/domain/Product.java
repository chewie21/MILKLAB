package com.example.milk.domain;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table (name = "milk_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String prodGroup;
    private String prodName;
    private Long prodCoast;
    private String prodInfo;

    public Product() {}

    public Product (String prodName, Long prodCoast, String prodGroup, String prodInfo) {
        this.prodName = prodName;
        this.prodGroup = prodGroup;
        this.prodCoast = prodCoast;
        this.prodInfo = prodInfo;
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

    public String getProdInfo() { return prodInfo; }

    public void setProdInfo(String prodInfo) { this.prodInfo = prodInfo; }
}
