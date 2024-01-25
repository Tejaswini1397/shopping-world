package com.example.shoppingworld.exception;

public class EmptyCardException extends RuntimeException{
    public EmptyCardException(String message){
        super(message);
    }
}
