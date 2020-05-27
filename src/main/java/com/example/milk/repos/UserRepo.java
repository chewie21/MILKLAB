package com.example.milk.repos;

import com.example.milk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAllById(Long id);
    List<User> findAllByName(String name);
    List<User> findAllBySurname(String surname);
    List<User> findAllByUsername(String username);
    List<User> findAllByEmail(String email);
    List<User> findAllByDate(String date);

}
