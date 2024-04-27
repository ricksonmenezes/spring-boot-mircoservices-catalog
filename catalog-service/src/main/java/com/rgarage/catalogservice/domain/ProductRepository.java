package com.rgarage.catalogservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository  extends JpaRepository<ProductEntity, Integer> {

    Optional<ProductEntity> findByCode(String code);

}
