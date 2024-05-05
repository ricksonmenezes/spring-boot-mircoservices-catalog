package com.rgarage.orderservice.domain;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message, Throwable t) {
        super(message, t);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

    public static OrderNotFoundException forCode(String code) {

        return new OrderNotFoundException("Order  not found for code " + code);
    }
}
