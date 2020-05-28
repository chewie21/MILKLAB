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
    List<User> findAllByActive (boolean active);

    @Query(nativeQuery = true,
            value = "select * from m_user join m_user_role mur on m_user.id = mur.user_id where mur.user_roles =:role")
    List<User> findAllByRole (@Param("role")String role);
    @Query(nativeQuery = true,
            value = "select count(*) from m_user")
    String countFirstUser();
    @Query(nativeQuery = true,
            value = "select count(*) from m_user where active =false")
    String countNotActiveUser();
}
