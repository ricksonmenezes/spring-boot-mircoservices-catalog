package com.rgarage.orderservice.web.controllers;

import com.rgarage.orderservice.domain.OrderService;
import com.rgarage.orderservice.domain.SecurityService;
import com.rgarage.orderservice.domain.models.CreateOrderRequest;
import com.rgarage.orderservice.domain.models.CreateOrderResponse;
import jakarta.validation.Valid;
import org.hibernate.query.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private  static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final SecurityService securityService;

    public OrderController(OrderService orderService, SecurityService securityService) {

        this.orderService = orderService;
        this.securityService = securityService;
    }

    @PostMapping("/createorders")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrder(@Valid  @RequestBody CreateOrderRequest request) {

        String userName = securityService.getUsrName();
        return orderService.createOrder(userName, request);


    }
}
