package com.playground.productservice.infrastructure.out.persistence;

import com.playground.core.domain.product.ProductCategory;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.infrastructure.dao.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistencePort {

    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public boolean isExistsProductCategoryByName(String name) {
        return productCategoryRepository.existsByName(name);
    }

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

}
