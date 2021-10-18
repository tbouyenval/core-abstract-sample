package com.example.demo.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class StockFullException  extends RuntimeException {
    private String message;
    public StockFullException(String message) {
        super(message);
        this.message = message;
    }
    public StockFullException() {
    }
}
