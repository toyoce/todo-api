package com.example.todoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import com.example.todoapi.service.UserService;
import com.example.todoapi.entity.User;
import com.example.todoapi.resource.MessageResource;

@RestController
@RequestMapping("/register")
public class UserRegisterController {
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResource registerUser(@Validated @RequestBody User user) {
        userService.create(user);
        return new MessageResource("Successfully registered");
    }
}
