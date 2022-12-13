package com.example.todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todoapi.entity.User;
import com.example.todoapi.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    public void create(User user) {
        User createdUser = new User(user.getUserId(), encoder.encode(user.getPassword()));
        repository.save(createdUser);
    }
}
