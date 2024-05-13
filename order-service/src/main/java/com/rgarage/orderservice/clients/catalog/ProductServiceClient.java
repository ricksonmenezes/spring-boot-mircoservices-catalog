package com.rgarage.orderservice.clients.catalog;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
public class ProductServiceClient {


    private static final Logger log = LoggerFactory.getLogger(ProductServiceClient.class);

    private final RestClient restClient;

    public ProductServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    /*
    adding circuit breaker to demonstrate how circuit state is opened if sliding window threshold for failures is crossed
     and state is left open for time duration mentioned.
    these statistics can be verified by goign to actuator/circuitbreaker
    * */
    @CircuitBreaker(name = "catalog-service")
    @Retry(name = "catalog-service", fallbackMethod = "getProductByCodeFallBack")
    public Optional<Product> getProductByCode(String code) {


            log.info("fetching product for code {}", code);
            var product = restClient.get().uri("/api/products/{code}", code)
                    .retrieve().body(Product.class);

            return Optional.ofNullable(product);

    }

    Optional<Product> getProductByCodeFallBack(String code, Throwable t) {

        log.error("getProductByCode could not be retried" + code);
        return Optional.empty();


    }

}
