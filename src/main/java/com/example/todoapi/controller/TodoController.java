package com.example.todoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.example.todoapi.service.TodoService;
import com.example.todoapi.entity.Todo;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    TodoService todoService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Todo> getAllTodos() {
        List<Todo> todos = todoService.findAll();
        return todos;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Todo getTodo(@PathVariable Integer id) {
        Todo todo = todoService.findById(id);
        return todo;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@Validated @RequestBody Todo todo) {
        Todo createdTodo = todoService.create(todo);
        return createdTodo;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Todo updateTodo(@PathVariable Integer id, @Validated @RequestBody Todo todo) {
        Todo updatedTodo = todoService.update(id, todo);
        return updatedTodo;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Integer id) {
        todoService.delete(id);
    }
}
