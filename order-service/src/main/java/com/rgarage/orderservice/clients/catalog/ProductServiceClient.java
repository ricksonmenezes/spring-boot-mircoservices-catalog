package com.rgarage.orderservice.clients.catalog;

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

    public Optional<Product> getProductByCode(String code) {

        try {
            log.info("fetching product for code {}", code);
            var product = restClient.get().uri("/api/products/{code}", code)
                    .retrieve().body(Product.class);

            return Optional.ofNullable(product);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
