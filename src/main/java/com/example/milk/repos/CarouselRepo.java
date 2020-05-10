package com.example.milk.repos;

import com.example.milk.domain.Carousel;
import org.springframework.data.repository.CrudRepository;

public interface CarouselRepo extends CrudRepository <Carousel, Long> {
}
