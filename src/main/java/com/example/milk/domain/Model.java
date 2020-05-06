package com.example.milk.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Model {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    protected Long id;

    public Model() {
    }

    public Model(Model model) {
        if(model != null) {
            this.id = model.id;
        }
    }

    public Model(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
