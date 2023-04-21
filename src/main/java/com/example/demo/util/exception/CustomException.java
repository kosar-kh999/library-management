package com.example.demo.util.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
public class CustomException {
    HttpStatus httpStatus;
    String message;
}
