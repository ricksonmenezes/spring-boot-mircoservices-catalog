package com.rgarage.catalogservice.domain;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message, Throwable t) {
        super(message, t);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException forCode(String code) {

        return new ProductNotFoundException("product not found for code " + code);
    }
}
