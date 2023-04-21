package com.example.demo.util.exception;

public class NotFoundPublisher extends RuntimeException {
    public NotFoundPublisher(String message) {
        super(message);
    }
}
