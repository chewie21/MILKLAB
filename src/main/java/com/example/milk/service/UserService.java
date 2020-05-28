package com.example.milk.service;

import com.example.milk.domain.UserRolesEnum;
import com.example.milk.domain.User;
import com.example.milk.repos.BasketRepo;
import com.example.milk.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BasketRepo basketRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    //Count
    public String countNotActiveUsers () {
        return userRepo.countNotActiveUser().equals("0") ? null : userRepo.countNotActiveUser();
    }

    //EditUser
    public boolean registration(User user) {
        if (checkAccountUsername(user) != null) {
            return false;
        }
        else if (checkAccountEmail(user) != null) {
            return false;
        }
        else {
            newAccount(user);
            return true;
        }
    }
    public void newAccount (User user) {
            user.setActive(true);
            if (userRepo.countFirstUser().equals("0")) {
                user.setUserRoles(Collections.singleton(UserRolesEnum.ADMIN));
            } else {
                user.setUserRoles(Collections.singleton(UserRolesEnum.USER));
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
    }
    public void blockAccount (User user) {
        if (basketRepo.findByUserId(user.getId()) != null) {
            basketRepo.delete(basketRepo.findByUserId(user.getId()));
        }
        user.setActive(false);
        userRepo.save(user);
    }
    public void activeAccount(User user) {
        user.setActive(true);
        userRepo.save(user);
    }
    public void saveAccount(User user, String name, String surname, String date) {
            user.setName(name);
            user.setSurname(surname);
            user.setDate(date);
            userRepo.save(user);
    }
    public void savePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
    }
    public void saveRole (User user, String role) {
        if(!user.getRole().equals(role)) {
            if (role.equals("ADMIN")) {
                user.getUserRoles().clear();
                user.getUserRoles().add(UserRolesEnum.ADMIN);
            }
            if (role.equals("USER")) {
                user.getUserRoles().clear();
                user.getUserRoles().add(UserRolesEnum.USER);
            }
        }
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
    public List<User> findByActive() { return userRepo.findAllByActive(true); }
    public List<User> findByNotActive() { return userRepo.findAllByActive(false); }
    public List<User> findAllByRole(String role) { return userRepo.findAllByRole(role);}

    //Check
    public User checkAccountUsername (User user) {
        return userRepo.findByUsername(user.getUsername());
    }
    public User checkAccountEmail (User user) {
        return userRepo.findByEmail(user.getEmail());
    }
    public boolean checkUsername (User user, String username) {
        if(user.getUsername().equals(username)) {
            return true;
        }
        else {
            User userFromDb = userRepo.findByUsername(username);
            if (userFromDb == null) {
                user.setUsername(username);
                return true;
            }
            else {
                return false;
            }
        }
    }
    public boolean checkEmail (User user, String email) {
        if(user.getEmail().equals(email)) {
            return true;
        }
        else {
            User userFromDb = userRepo.findByEmail(email);
            if (userFromDb == null) {
                user.setEmail(email);
                return true;
            }
            else {
                return false;
            }
        }
    }

}
