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

    public OrderService(OrderRepository repository) {
        this.orderRepository = repository;
    }

    public CreateOrderResponse createOrder(String userName, CreateOrderRequest request) {

        OrderEntity order = OrderMapper.convertToEntity(request);
        order.setUserName(userName);
      OrderEntity savedOrder =   orderRepository.save(order);

        log.info("created order with ordernumber ={}", savedOrder.getOrderNumber());
        return new CreateOrderResponse(savedOrder.getOrderNumber());
    }
}
