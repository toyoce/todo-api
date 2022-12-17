package com.example.todoapi.resource;

import java.io.Serializable;

public class TodoRequestResource implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;

    public TodoRequestResource() {}

    public TodoRequestResource(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
