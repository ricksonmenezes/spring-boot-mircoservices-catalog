package com.rgarage.catalogservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<ProductEntity, Integer> {


}
