package com.example.milk.repos;

import com.example.milk.domain.Carousel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarouselRepo extends CrudRepository <Carousel, Long> {

    @Query(nativeQuery = true,
            value = "select * from m_carousel where carousel_group = 1")
    List<Carousel> findAllByCarouselGroupMenu();

    @Query(nativeQuery = true,
            value = "select * from m_carousel where carousel_group = 2")
    List<Carousel> findAllByCarouselGroupContacts();
}
