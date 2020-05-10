package com.example.milk.domain;

import javax.persistence.*;

@Entity
@Table(name = "product_group")
public class ProductGroup  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String prodGroup;

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

}
