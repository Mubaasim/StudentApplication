package com.sra.studentapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sra.studentapp.model.User;
import com.sra.studentapp.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

//Implemented class to test in an isolated manner

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/registertest")
    public User register(@RequestBody User user) {
        return userService.register(user);

    }

    @PostMapping("/logintest")
    public ResponseEntity<String> login(@RequestBody User user, HttpServletResponse response) {

        return userService.verify(user,response);
    }
}