package com.epam.lab.mongo.MongoDB.controllers;

import com.epam.lab.mongo.MongoDB.dto.User;
import com.epam.lab.mongo.MongoDB.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class controller {

    @Autowired
    private IUserService userService;

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
       return userService.getAllUsers();
    }

}
