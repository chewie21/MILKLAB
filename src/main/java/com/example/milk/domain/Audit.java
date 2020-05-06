package com.example.milk.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class Audit extends Model {

    @CreationTimestamp
    @Column(updatable = false)
    protected LocalDate createdWhen;
    @Column(updatable = false)
    protected String createdBy;
    @UpdateTimestamp
    protected LocalDate updateWhen;

    public Audit() {}

    protected Audit(Long id) {
        super(id);
    }

    protected Audit(Model model) {
        super(model);
    }
}
