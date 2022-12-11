package com.example.todoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todoapi.entity.Todo;
import com.example.todoapi.repository.TodoRepository;

@Service
public class TodoService {
    @Autowired
    private TodoRepository repository;

    @Transactional(readOnly = true)
    public Todo findById(Integer id) {
        Todo todo = repository.getReferenceById(id);
        return todo;
    }

    @Transactional
    public Todo create(String title) {
        Todo todo = new Todo(title);
        return repository.save(todo);
    }
}
