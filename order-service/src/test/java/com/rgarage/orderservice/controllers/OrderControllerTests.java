package com.rgarage.orderservice.controllers;

import com.rgarage.orderservice.AbstractIT;
import com.rgarage.orderservice.testdata.TestDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class OrderControllerTests  extends AbstractIT {

    @Nested
    class CreateOrderTests {

        @Test
    void shouldCreateOrderSuccessfully() {

            var payload = """
                    {
                    "deliveryAddress":{
                    "addressLine1":"reddington",
                    "addressLine2":"Santa Avenue",
                    "city":"San Diego",
                    "state":"CA",
                    "zipCode": "54023",
                    "country":"US"
                    },
                    "customer":{
                    "name": "Ricka Men",
                    "email":"r@hifinite.com",
                    "phone":"+919769245984"
                    },
                    "items":[{
                    "code":"P101",
                    "name":"Product 1",
                    "price":34.3,
                    "quantity":1
                    }]
                    }
                    """;

            given().contentType(ContentType.JSON)
                    .body(payload)
                    .post("/api/orders/createorders")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("orderNumber", notNullValue());


        }

        @Test
        void shouldReturnBadRequestWhenMandatoryDataIsMissing() {

            var payload = TestDataFactory.createOrderRequestWithInvalidCustomer();
            given().contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post("api/orders/createorders")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());

        }
    }
}
