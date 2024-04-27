package com.rgarage.catalogservice.web.controllers;

import com.rgarage.catalogservice.AbstractIT;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest  extends AbstractIT {

    @Test
    void shouldReturnProducts() {

        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("totalElements",is(15))
                .body("totalPages", is(2))
                .body("pageNumber", is(1))
                .body("isFirst", is(true))
                .body("isLast",is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));




    }


}