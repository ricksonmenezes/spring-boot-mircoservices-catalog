package com.rgarage.orderservice.web.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.rgarage.orderservice.ApplicationProperties;

@RestController
public class RabbitMQDemoController {

    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties properties;

    public RabbitMQDemoController(RabbitTemplate rabbitTemplate, ApplicationProperties properties) {
        this.properties= properties;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping(value = "/send")
    public  void  sendMessage(@RequestBody MyMessage myMessage) {

        rabbitTemplate.convertAndSend(properties.orderEventsExchange(), myMessage.routingKey(), myMessage.payload());
    }

    public record MyMessage ( String routingKey, MyPayload payload){}

    public record MyPayload(String content){}

}
