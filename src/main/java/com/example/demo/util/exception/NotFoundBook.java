package com.example.demo.util.exception;

public class NotFoundBook extends RuntimeException {
    public NotFoundBook(String message) {
        super(message);
    }
}
