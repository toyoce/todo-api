package com.example.todoapi.service;

import java.util.List;

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
    public List<Todo> findAll() {
        List<Todo> todos = repository.findAll();
        return todos;
    }

    @Transactional(readOnly = true)
    public Todo findById(Integer id) {
        Todo todo = repository.findById(id).get();
        return todo;
    }

    @Transactional
    public Todo create(Todo todo) {
        Todo createdTodo = repository.save(todo);
        return createdTodo;
    }

    @Transactional
    public Todo update(Integer id, Todo todo) {
        Todo updatedTodo = repository.findById(id).get();
        updatedTodo.setTitle(todo.getTitle());
        return updatedTodo;
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
