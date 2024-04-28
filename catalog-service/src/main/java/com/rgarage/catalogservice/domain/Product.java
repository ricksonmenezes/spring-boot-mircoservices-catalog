package com.rgarage.catalogservice.domain;

public record Product(String code, String name, String description, String imageUrl, java.math.BigDecimal price) {}
