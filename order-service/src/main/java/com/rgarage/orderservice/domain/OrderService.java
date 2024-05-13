package com.rgarage.orderservice.domain;

import com.rgarage.orderservice.domain.models.CreateOrderRequest;
import com.rgarage.orderservice.domain.models.CreateOrderResponse;
import jakarta.persistence.criteria.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;

    private final OrderValidator orderValidator;

    public OrderService(OrderRepository repository, OrderValidator orderValidator) {
        this.orderRepository = repository;
        this.orderValidator = orderValidator;
    }

    public CreateOrderResponse createOrder(String userName, CreateOrderRequest request) {

        orderValidator.validate(request);
        OrderEntity order = OrderMapper.convertToEntity(request);
        order.setUserName(userName);
      OrderEntity savedOrder =   orderRepository.save(order);

        log.info("created order with ordernumber ={}", savedOrder.getOrderNumber());
        return new CreateOrderResponse(savedOrder.getOrderNumber());
    }
}
