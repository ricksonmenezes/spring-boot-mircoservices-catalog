package com.rgarage.orderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rgarage.orderservice.ApplicationProperties;

@Configuration
public class RabbitMQConfig {

    private final ApplicationProperties  properties;

    public RabbitMQConfig(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(properties.orderEventsExchange());
    }

    @Bean
    Queue newOrdersQueue() {
        return QueueBuilder.durable(properties.newOrdersQueue()).build();
    }

    @Bean
    Binding newOrdersQueueBinding() {

        return BindingBuilder.bind(newOrdersQueue()).to(exchange()).with(properties.newOrdersQueue());
    }

    @Bean
    Queue deliveredOrdersQueue() {
        return QueueBuilder.durable(properties.deliveredOrdersQueue()).build();
    }

    @Bean
    Binding deliveredOrdersQueueBinding() {

        return BindingBuilder.bind(newOrdersQueue()).to(exchange()).with(properties.deliveredOrdersQueue());
    }

    @Bean
    Queue cancelledOrdersQueue() {
        return QueueBuilder.durable(properties.cancelledOrdersQueue()).build();
    }

    @Bean
    Binding cancelledOrdersQueueBinding() {

        return BindingBuilder.bind(newOrdersQueue()).to(exchange()).with(properties.cancelledOrdersQueue());
    }


    @Bean
    Queue errorOrdersQueue() {
        return QueueBuilder.durable(properties.errorOrdersQueue()).build();
    }

    @Bean
    Binding errorOrdersQueueBinding() {

        return BindingBuilder.bind(newOrdersQueue()).to(exchange()).with(properties.errorOrdersQueue());
    }


    @Bean
    RabbitTemplate rabbitTemplate (ConnectionFactory connectionFactory , ObjectMapper objectMapper ) {

        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter(objectMapper));
        return rabbitTemplate;

    }

    @Bean
    Jackson2JsonMessageConverter jacksonConverter(ObjectMapper objectMapper) {

        return new Jackson2JsonMessageConverter(objectMapper);
    }


}
