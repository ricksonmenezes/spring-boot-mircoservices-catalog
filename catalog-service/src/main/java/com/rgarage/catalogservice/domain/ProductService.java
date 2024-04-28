package com.rgarage.catalogservice.domain;

import com.rgarage.catalogservice.ApplicationProperties;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ApplicationProperties properties;

    public ProductService(ProductRepository productRepository, ApplicationProperties properties) {

        this.productRepository = productRepository;
        this.properties = properties;
    }

    public PagedResult<Product> getProducts(int page) {

        Sort sort = Sort.by("name").ascending();
        page = page <= 1 ? 0 : page - 1;
        PageRequest pageRequest = PageRequest.of(page, properties.pageSize(), sort);
        var pages = productRepository.findAll(pageRequest).map(ProductMapper::toProduct);
        return new PagedResult<>(
                pages.getContent(),
                pages.getTotalElements(),
                pages.getNumber() + 1,
                pages.getTotalPages(),
                pages.isFirst(),
                pages.isLast(),
                pages.hasNext(),
                pages.hasPrevious());
    }

    public Optional<Product> getProductByCode(String code) {

        return productRepository.findByCode(code).map(ProductMapper::toProduct);
    }
}
