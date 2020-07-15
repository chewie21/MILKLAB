package com.example.milk.domain;

import javax.persistence.*;

@Entity
@Table(name = "m_carousel")
public class Carousel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long carouselGroup;
    private String carouselInfo;
    private String carouselImg;

    public Carousel (){}

    public Carousel(Long carouselGroup, String carouselInfo) {
        this.carouselGroup = carouselGroup;
        this.carouselInfo = carouselInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarouselGroup() {
        return carouselGroup;
    }

    public void setCarouselGroup(Long carouselGroup) {
        this.carouselGroup = carouselGroup;
    }

    public String getCarouselInfo() {
        return carouselInfo;
    }

    public void setCarouselInfo(String carouselInfo) {
        this.carouselInfo = carouselInfo;
    }

    public String getCarouselImg() {
        return carouselImg;
    }

    public void setCarouselImg(String carouselImg) {
        this.carouselImg = carouselImg;
    }

}
