package com.example.shoppingworld.exception;

import org.springframework.data.jpa.repository.JpaRepository;

public class InvalidCardException extends RuntimeException {
    public InvalidCardException(String message){
        super(message);
    }
}
