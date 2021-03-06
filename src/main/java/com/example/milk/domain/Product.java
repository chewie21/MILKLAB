package com.example.milk.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "m_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String prodName;
    private String prodInfo;
    private Long prodCoast;
    private String prodImg;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ProductStatusEnum status;

    @ManyToOne
    private ProductGroup productGroup;

    public Product() {}

    public Product(String prodName, String prodInfo, Long prodCoast, ProductStatusEnum status) {
        this.prodName = prodName;
        this.prodInfo = prodInfo;
        this.prodCoast = prodCoast;
        this.status = status;
    }

    public Product(Long productId) {
        this.id = productId;
    }

    public boolean isActive() { return status.equals(ProductStatusEnum.ACTIVE);}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdInfo() {
        return prodInfo;
    }

    public void setProdInfo(String prodInfo) {
        this.prodInfo = prodInfo;
    }

    public Long getProdCoast() {
        return prodCoast;
    }

    public void setProdCoast(Long prodCoast) {
        this.prodCoast = prodCoast;
    }

    public String getProdImg() {
        return prodImg;
    }

    public void setProdImg(String prodImg) {
        this.prodImg = prodImg;
    }

    public ProductStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ProductStatusEnum status) {
        this.status = status;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }
}
