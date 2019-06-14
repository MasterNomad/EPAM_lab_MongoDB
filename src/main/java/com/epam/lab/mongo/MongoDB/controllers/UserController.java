package com.epam.lab.mongo.MongoDB.controllers;

import com.epam.lab.mongo.MongoDB.dto.User;
import com.epam.lab.mongo.MongoDB.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @ModelAttribute
    LocalDate initLocalDate() {
        return LocalDate.now();
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUsersWithFriend")
    public List<User> getUsersWithFriend(long friendId) {
        return userService.getUsersWithFriend(friendId);
    }

    @GetMapping("/getUserWithMaxRating")
    public User getUserWithMaxRating() {
        return userService.getUserWithMaxRating();
    }

    @PostMapping("/saveUser")
    public void saveUser(String name, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ModelAttribute LocalDate birthDate) {
        userService.saveUser(new User(name, birthDate));
    }

}
