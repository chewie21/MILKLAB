package com.example.milk.domain;

import javax.persistence.*;

@Entity
@Table( name = "m_review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String name;
    private String date;

    private Long rating;

    private String shortText;
    @Column(columnDefinition = "LONGTEXT")
    private String text;

    private boolean discount;
    private boolean view;

    public Review() {
    }

    public Review(String username, String name, String date, Long rating, String text, String shortText) {
        this.username = username;
        this.name = name;
        this.date = date;
        this.rating = rating;
        this.text = text;
        this.shortText = shortText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }
}
