package com.rgarage.orderservice.domain;

import com.rgarage.orderservice.clients.catalog.Product;
import com.rgarage.orderservice.clients.catalog.ProductServiceClient;
import com.rgarage.orderservice.domain.models.CreateOrderRequest;
import com.rgarage.orderservice.domain.models.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class OrderValidator {

    private final Logger log = LoggerFactory.getLogger(OrderValidator.class);

    private  final  ProductServiceClient productServiceClient;

    public OrderValidator(ProductServiceClient productServiceClient) {

        this.productServiceClient = productServiceClient;
    }

     public void validate(CreateOrderRequest request) {

        Set<OrderItem> itemSet = request.items();
             for (OrderItem item : itemSet) {

                 Product product = productServiceClient.getProductByCode(item.code())
                         .orElseThrow(() -> new InvalidOrderException("Product not found for code:" + item.code()));

                 if (item.price().compareTo(product.price()) != 0) {

                     log.error("product price not matching actual price {} received price {}", product.price(), item.price());
                     throw new InvalidOrderException("produce price not matching");
                 }
             }

         }


}
