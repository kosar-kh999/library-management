package com.example.demo.util.exception;

public class NotFoundAuthor extends RuntimeException {
    public NotFoundAuthor(String message) {
        super(message);
    }
}
