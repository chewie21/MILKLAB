package com.example.milk.repos;

import com.example.milk.domain.Group;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupRepo extends CrudRepository <Group, Long> {
    List<Group> findAll();


}
