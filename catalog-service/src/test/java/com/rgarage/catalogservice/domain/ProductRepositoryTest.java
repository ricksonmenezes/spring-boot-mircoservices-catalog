package com.rgarage.catalogservice.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.rgarage.catalogservice.ContainersConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
@DataJpaTest(
        properties = {
            "spring.test.database.replace=none",
            // "spring.datasource.url=jdbc:tc:postgres:16-alpine:///db",
        })
@Import(
        ContainersConfig
                .class) // if container config contains connection to rabbit mq, it is unnecessary for repository test
// use "spring.datasource.url=jdbc:tc:postgres:16-alpine:///db istead
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    // not necessary totest the framework APi
    /*@Test
        void getAllProducts() {
    List<ProductEntity> productEntityList =         productRepository.findAll();
        assertThat(productEntityList).hasSize(15);
        }*/

}
