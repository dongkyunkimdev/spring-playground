package com.playground.productservice.infrastructure.out.persistence;

import com.playground.core.domain.product.ProductCategory;
import com.playground.productservice.application.port.out.persistence.ProductPersistencePort;
import com.playground.productservice.infrastructure.dao.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistencePort {

    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public boolean isExistsProductCategoryByName(String name) {
        return productCategoryRepository.existsByName(name);
    }

    @Override
    public Optional<ProductCategory> getProductCategoryById(Long productCategoryId) {
        return productCategoryRepository.findById(productCategoryId);
    }

    @Override
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

}
