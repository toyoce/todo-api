package com.example.todoapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.todoapi.service.TodoService;
import com.example.todoapi.entity.Todo;
import com.example.todoapi.resource.TodoResource;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    TodoService todoService;

    @RequestMapping(method = RequestMethod.GET)
    public List<TodoResource> getAllTodos() {
        List<Todo> todos = todoService.findAll();
        return todos.stream().map(TodoResource::new).toList();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TodoResource> getTodo(@PathVariable Integer id) {
        Optional<Todo> todo = todoService.findById(id);
        return todo.map(TodoResource::new).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResource createTodo(@Validated @RequestBody TodoResource todoResource, @AuthenticationPrincipal UserDetails userDetails) {
        Todo todo = new Todo(todoResource.getTitle());
        String userId = userDetails.getUsername();
        Todo createdTodo = todoService.create(todo, userId);
        return new TodoResource(createdTodo);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TodoResource> updateTodo(@PathVariable Integer id, @Validated @RequestBody TodoResource todoResource) {
        Todo todo = new Todo(todoResource.getTitle());
        Optional<Todo> updatedTodo = todoService.update(id, todo);
        return updatedTodo.map(TodoResource::new).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Integer id) {
        todoService.delete(id);
    }
}
