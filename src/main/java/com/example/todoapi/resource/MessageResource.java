package com.example.todoapi.resource;

import java.io.Serializable;

public class MessageResource implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;

    public MessageResource() {}

    public MessageResource(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
