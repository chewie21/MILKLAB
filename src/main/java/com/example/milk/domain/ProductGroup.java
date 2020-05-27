package com.example.milk.domain;

import javax.persistence.*;

@Entity
@Table(name = "m_group_products")
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group productGroup;

    public ProductGroup() {}

    public ProductGroup(Group productGroup, Product product) {
        this.productGroup = productGroup;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Group getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(Group productGroup) {
        this.productGroup = productGroup;
    }
}
