package com.example.productservicenaman.exceptions;

public class ProductNotExistException extends Exception{
    public ProductNotExistException(String message){
        super(message);
    }
}
