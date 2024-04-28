package com.rgarage.catalogservice.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    Optional<ProductEntity> findByCode(String code);
}
