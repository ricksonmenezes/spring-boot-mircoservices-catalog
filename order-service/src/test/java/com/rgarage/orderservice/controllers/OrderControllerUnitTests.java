package com.rgarage.orderservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgarage.orderservice.domain.OrderService;
import com.rgarage.orderservice.domain.SecurityService;
import com.rgarage.orderservice.domain.models.CreateOrderRequest;
import com.rgarage.orderservice.web.controllers.OrderController;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.BDDMockito;
import org.apache.commons.lang3.stream.Streams;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.stream.Stream;

import static com.rgarage.orderservice.testdata.TestDataFactory.*;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerUnitTests {

    @MockBean
    OrderService orderService;

    @MockBean
    SecurityService securityService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

@BeforeEach
    void setUp() {
        given(securityService.getUsrName()).willReturn("rix");
    }

    @MethodSource("withUnitTestProvider")
    @ParameterizedTest(name = "[{index}]-{0}")
void shouldReturnBadRequestWhenOrderPayloadIsInvalid(CreateOrderRequest request) throws Exception {

    System.out.println(request);

    given(orderService.createOrder(eq("rix"),any(CreateOrderRequest.class)))
                .willReturn(null);

        mockMvc.perform(post("/api/orders/createorders")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
}



    static  Stream<Arguments> withUnitTestProvider() {

        return Streams.of(arguments(named("Order with invalid Customer", createOrderRequestWithInvalidCustomer())),
                arguments(named("Order with invalid Delivery Address", createOrderRequestWithInvalidDeliveryAddress())),
                arguments(named("Order with no items", createOrderRequestWithNoItems()))
        );
    }
}
