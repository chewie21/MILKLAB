package com.example.milk.domain;

import javax.persistence.*;

@Entity
@Table(name = "m_carousel")
public class Carousel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String carouselInfo;
    private String carouselImg;

    public Carousel (){}

    public Carousel(String carouselInfo) {
        this.carouselInfo = carouselInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
