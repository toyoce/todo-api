package com.example.todoapi.exception;

import java.io.Serializable;

public class ApiError implements Serializable {
    private static final long serialVersionUID = 1L;

    private int status;
    private String message;

    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
