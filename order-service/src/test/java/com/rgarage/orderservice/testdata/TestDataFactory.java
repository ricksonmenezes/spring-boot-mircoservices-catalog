package com.rgarage.orderservice.testdata;

import com.rgarage.orderservice.domain.models.Address;
import com.rgarage.orderservice.domain.models.CreateOrderRequest;
import com.rgarage.orderservice.domain.models.Customer;
import com.rgarage.orderservice.domain.models.OrderItem;
import org.instancio.Instancio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.instancio.Select.field;

public class TestDataFactory {

    static final List<String> VALID_COUNTRIES = List.of("India", "Germany");
    static final Set<OrderItem> VALID_ORDER_ITEMS =
            Set.of(new OrderItem("P100", "Product 1", new BigDecimal("25.50"), 1));
    public static CreateOrderRequest createOrderRequestWithInvalidCustomer() {
        return Instancio.of(CreateOrderRequest.class)
                .generate(field(Customer::email), gen -> gen.text().pattern("#c#c#c#c#d#d@mail.com"))
                .set(field(Customer::phone), "")
                .generate(field(Address::country), gen -> gen.oneOf(VALID_COUNTRIES))
                .set(field(CreateOrderRequest::items), VALID_ORDER_ITEMS)
                .create();
    }

    public static CreateOrderRequest createOrderRequestWithInvalidDeliveryAddress() {
        return Instancio.of(CreateOrderRequest.class)
                .generate(field(Customer::email), gen -> gen.text().pattern("#c#c#c#c#d#d@mail.com"))
                .set(field(Address::country), "")
                .set(field(CreateOrderRequest::items), VALID_ORDER_ITEMS)
                .create();
    }

    public static CreateOrderRequest createOrderRequestWithNoItems() {
        return Instancio.of(CreateOrderRequest.class)
                .generate(field(Customer::email), gen -> gen.text().pattern("#c#c#c#c#d#d@mail.com"))
                .generate(field(Address::country), gen-> gen.oneOf(VALID_COUNTRIES))
                .set(field(CreateOrderRequest::items), Set.of())
                .create();
    }
}