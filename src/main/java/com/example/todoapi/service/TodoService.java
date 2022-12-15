package com.example.todoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todoapi.entity.Todo;
import com.example.todoapi.entity.User;
import com.example.todoapi.repository.TodoRepository;
import com.example.todoapi.repository.UserRepository;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<Todo> findAll() {
        List<Todo> todos = todoRepository.findAll();
        return todos;
    }

    @Transactional(readOnly = true)
    public Optional<Todo> findById(Integer id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo;
    }

    @Transactional
    public Todo create(Todo todo, String userId) {
        User user = userRepository.findById(userId).get();
        todo.setUser(user);
        Todo createdTodo = todoRepository.save(todo);
        return createdTodo;
    }

    @Transactional
    public Optional<Todo> update(Integer id, Todo todo) {
        Optional<Todo> updatedTodoOpt = todoRepository.findById(id);
        updatedTodoOpt.ifPresent(updatedTodo -> updatedTodo.setTitle(todo.getTitle()));
        return updatedTodoOpt;
    }

    @Transactional
    public void delete(Integer id) {
        todoRepository.deleteById(id);
    }
}
