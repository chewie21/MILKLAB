package com.example.milk.repos;

import com.example.milk.domain.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepo extends CrudRepository <Order, Long> {


}
