package com.rgarage.orderservice.domain;

public class InvalidOrderException extends RuntimeException {

    public InvalidOrderException(String msg) {
        super(msg);
    }
}
