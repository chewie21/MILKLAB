package com.example.milk.service;

import com.example.milk.domain.UserRole;
import com.example.milk.domain.User;
import com.example.milk.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }


    //EditUser
    public void newAccount (User user) {
            user.setActive(true);
            user.setUserRoles(Collections.singleton(UserRole.USER));
            userRepo.save(user);
    }
    public void deleteAccount (User user) {
        userRepo.delete(user);
    }
    public User checkAccount (User user) {
        return userRepo.findByUsername(user.getUsername());
    }
    public void saveAccount(User user, String name, String surname, String username, String email, String date, String password) {
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setEmail(email);
        user.setDate(date);
        user.setPassword(password);
        userRepo.save(user);
    }

    //FindUser
    public List<User> findAll() { return userRepo.findAll(); }
    public List<User> findAllByName(String name) { return userRepo.findAllByName(name); }
    public List<User> findAllById(Long id) {
        return userRepo.findAllById(id);
    }
    public List<User> findAllBySurname(String surname) {
        return userRepo.findAllBySurname(surname);
    }
    public List<User> findAllByUsername(String username) {
        return userRepo.findAllByUsername(username);
    }
    public List<User> findAllByEmail (String email) {
        return userRepo.findAllByEmail(email);
    }
    public List<User> findAllByDate (String date) { return userRepo.findAllByDate(date); }

}
