package com.example.milk.repos;

import com.example.milk.domain.Basket;
import com.example.milk.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BasketRepo extends CrudRepository <Basket, Long> {
    List<Basket> findAllByUserId (Long id);

}

