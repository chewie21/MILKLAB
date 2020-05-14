package com.example.milk.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_group")
public class ProductGroup  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String prodGroup;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();


    public ProductGroup() {}

    public ProductGroup(String prodGroup) {
        this.prodGroup = prodGroup;
    }
    public ProductGroup(Long groupId) {
        this.id = groupId;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean contains(Long productId) {
        return true;
    }
}
