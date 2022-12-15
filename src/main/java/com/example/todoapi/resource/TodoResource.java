package com.example.todoapi.resource;

import java.io.Serializable;

import com.example.todoapi.entity.Todo;

public class TodoResource implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private String userId;

    public TodoResource() {}

    public TodoResource(Integer id, String title, String userId) {
        this.id = id;
        this.title = title;
        this.userId = userId;
    }

    public TodoResource(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.userId = todo.getUser().getUserId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
